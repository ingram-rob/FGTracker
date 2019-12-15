package com.inurpocketapps.fgtracker;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class AerobicActivity extends AppCompatActivity {
    // Static Variables
    private static final String CLASS_COL = "CLASSES";
    private static final String SCHOOL_COL = "SCHOOLS";
    private static final String GRADE_COL = "Grades";
    private static final String STUDENT_COL = "STUDENTS";
    private static final String TEST_COL = "TESTS";
    private static final String TEST_NAME = "AEROBIC_TEST";
    private static final String AEROBIC_COL = "AEROBIC_RESULTS";

    // Persistant variables
    private String email;
    private String userName;
    private String schoolName;

    //The current grade number
    private int gradeNumber;

    //Classroom Name
    private String classroomName;

    //Student name
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

    //List to store all aerobic tests
    List<AerobicTest> aerobicTestList = new ArrayList<>();

    // View Holders
    private RecyclerView resView;
    private RecyclerView.Adapter adapt;
    private RecyclerView.LayoutManager resViewLayMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aerobic);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize RecyclerView
        resView = findViewById(R.id.aerobicResView);
        resView.setHasFixedSize(true);

        // Use a linear layout manager for the RecyclerView
        resViewLayMan = new LinearLayoutManager(this);
        resView.setLayoutManager(resViewLayMan);

        // Set View Adapter
        adapt = new AerobicTestAdapter(aerobicTestList, this);
        resView.setAdapter(adapt);

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
        flexTestCol = testCollection.document(TEST_NAME).collection(AEROBIC_COL);
    }
}
