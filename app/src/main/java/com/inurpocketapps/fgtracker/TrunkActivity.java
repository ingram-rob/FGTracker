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

public class TrunkActivity extends AppCompatActivity implements AddTrunkDialog.addTrunkTestListener {

    // Static Variables
    private static final String CLASS_COL = "CLASSES";
    private static final String SCHOOL_COL = "SCHOOLS";
    private static final String GRADE_COL = "Grades";
    private static final String STUDENT_COL = "STUDENTS";
    private static final String TEST_COL = "TESTS";
    private static final String TEST_NAME = "FLEX_TEST";
    private static final String TRUNK_COL = "TRUNK_RESULTS";

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
    private CollectionReference trunkTestCollection;

    // Array to store all trunk lift tests
    private List<TrunkTest> trunkTests = new ArrayList<>();

    // View Holders
    private RecyclerView resView;
    private RecyclerView.Adapter adapt;
    private RecyclerView.LayoutManager resViewLayMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trunk);

        // Initialize RecyclerView
        resView = findViewById(R.id.trunkResView);
        resView.setHasFixedSize(true);

        // Use a linear layout manager for the RecyclerView
        resViewLayMan = new LinearLayoutManager(this);
        resView.setLayoutManager(resViewLayMan);

        // Set View Adapter
        adapt = new TrunkTestAdapter(trunkTests);
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
        trunkTestCollection = testCollection.document(TEST_NAME).collection(TRUNK_COL);

        initializeTestResults();
    }

    public void initializeTestResults(){
        // Get the collection of flexibility tests
        trunkTestCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        TrunkTest test = document.toObject(TrunkTest.class);
                        trunkTests.add(test);
                        adapt.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    public void addTestResult(View view){
        // Create alert dialog for adding flex test results
        AddTrunkDialog trunkDlog = new AddTrunkDialog();
        trunkDlog.show(getSupportFragmentManager(), "newFlexTest");
    }

    @Override
    public void onAddTruckTestClick(String result) {
        TrunkTest newTest = new TrunkTest();
        newTest.setTrunkLift(result);

        trunkTests.add(newTest);
        adapt.notifyDataSetChanged();

        trunkTestCollection.document(newTest.getDate()).set(newTest);
    }
}
