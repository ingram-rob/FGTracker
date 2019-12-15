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

public class UpperBodyActivity extends AppCompatActivity implements AddUpperDialog.addUpperTestListener {

    // Static Variables
    private static final String CLASS_COL = "CLASSES";
    private static final String SCHOOL_COL = "SCHOOLS";
    private static final String GRADE_COL = "Grades";
    private static final String STUDENT_COL = "STUDENTS";
    private static final String TEST_COL = "TESTS";
    private static final String TEST_NAME = "UPPER_TEST";
    private static final String UPPER_COL = "UPPER_RESULTS";

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
    private CollectionReference upperTestCol;

    // Array to store all flexibility tests
    private List<UpperBodyTest> upperTests = new ArrayList<>();

    // View Holders
    private RecyclerView resView;
    private RecyclerView.Adapter adapt;
    private RecyclerView.LayoutManager resViewLayMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upper_body);

        // Initialize RecyclerView
        resView = findViewById(R.id.upperResView);
        resView.setHasFixedSize(true);

        // Use a linear layout manager for the RecyclerView
        resViewLayMan = new LinearLayoutManager(this);
        resView.setLayoutManager(resViewLayMan);

        // Set View Adapter
        adapt = new UpperTestAdapter(upperTests);
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
        upperTestCol = testCollection.document(TEST_NAME).collection(UPPER_COL);

        initializeTestResults();

    }

    public void initializeTestResults(){
        // Get the collection of flexibility tests
        upperTestCol.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        UpperBodyTest test = document.toObject(UpperBodyTest.class);
                        upperTests.add(test);
                        adapt.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    public void addTestResult(View view){
        // Create alert dialog for adding flex test results
        AddUpperDialog upperDialog = new AddUpperDialog();
        upperDialog.show(getSupportFragmentManager(), "newFlexTest");
    }

    @Override
    public void onAddUpperTestClick(String pushUps, String pullUps, String armHang) {
        UpperBodyTest newTest = new UpperBodyTest();
        newTest.setPushUps(pushUps);
        newTest.setPullUps(pullUps);
        newTest.setArmHang(armHang);

        upperTests.add(newTest);
        adapt.notifyDataSetChanged();

        upperTestCol.document(newTest.getDate()).set(newTest);
    }
}
