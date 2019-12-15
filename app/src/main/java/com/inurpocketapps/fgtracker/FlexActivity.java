package com.inurpocketapps.fgtracker;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FlexActivity extends AppCompatActivity implements AddFlexDialog.addFlexTestListener {

    // Static Variables
    private static final String CLASS_COL = "CLASSES";
    private static final String SCHOOL_COL = "SCHOOLS";
    private static final String GRADE_COL = "Grades";
    private static final String STUDENT_COL = "STUDENTS";
    private static final String TEST_COL = "TESTS";
    private static final String TEST_NAME = "FLEX_TEST";
    private static final String FLEX_COL = "FLEX_RESULTS";

    // Persistant variables
    private String email;
    private String userName;
    private String schoolName;
    private int gradeNumber;
    private String classroomName;
    private String studentName;

    // Firebase Firestore database
    private FirebaseFirestore db;
    private CollectionReference schoolCollection;
    private DocumentReference userDoc;
    private CollectionReference gradeCollection;
    private CollectionReference classroomCollection;
    private CollectionReference studentCollection;
    private CollectionReference testCollection;
    private CollectionReference flexTestCol;

    // Array to store all flexibility tests
    private List<FlexibilityTest> flexTests = new ArrayList<>();

    // View Holders
    private RecyclerView resView;
    private RecyclerView.Adapter adapt;
    private RecyclerView.LayoutManager resViewLayMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flex);

        // Initialize RecyclerView
        resView = findViewById(R.id.flexResView);
        resView.setHasFixedSize(true);

        // Use a linear layout manager for the RecyclerView
        resViewLayMan = new LinearLayoutManager(this);
        resView.setLayoutManager(resViewLayMan);

        // Set View Adapter
        adapt = new FlexTestAdapter(flexTests, this);
        resView.setAdapter(adapt);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        schoolName = extras.getString("SCHOOL_NAME");
        userName = extras.getString("USER_NAME");
        gradeNumber = extras.getInt("GRADE_NUMBER");
        classroomName = extras.getString("CLASS_NAME");
        studentName = extras.getString("STUDENT_NAME");

        //Get Database
        db = FirebaseFirestore.getInstance();
        userDoc = db.collection("USERS").document(userName);
        schoolCollection = userDoc.collection(SCHOOL_COL);
        gradeCollection = schoolCollection.document(schoolName).collection(GRADE_COL);
        classroomCollection = gradeCollection.document("Grade " + gradeNumber).collection(CLASS_COL);
        studentCollection = classroomCollection.document(classroomName).collection(STUDENT_COL);
        testCollection = studentCollection.document(studentName).collection(TEST_COL);
        flexTestCol = testCollection.document(TEST_NAME).collection(FLEX_COL);

        initializeTestResults();

    }

    public void addTestResult(View view){
        // Create alert dialog for adding flex test results
        AddFlexDialog flexDlog = new AddFlexDialog();
        flexDlog.show(getSupportFragmentManager(), "newFlexTest");
    }

    public void initializeTestResults(){
        // Get the collection of flexibility tests
        flexTestCol.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        FlexibilityTest test = document.toObject(FlexibilityTest.class);
                        flexTests.add(test);
                        adapt.notifyDataSetChanged();
                    }
                }
            }
        });

    }

    @Override
    public void onAddFlexTestClick(float lReach, float rReach, boolean lPass, boolean rPass) {
        FlexibilityTest newTest = new FlexibilityTest();
        newTest.setLeftPass(lPass);
        newTest.setRightPass(rPass);
        newTest.setSitAndReachLeft(Float.toString(lReach));
        newTest.setSitAndReachRight(Float.toString(rReach));

        flexTests.add(newTest);
        adapt.notifyDataSetChanged();

        flexTestCol.document(newTest.getDate()).set(newTest);
    }
}
