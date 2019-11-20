package com.inurpocketapps.fgtracker;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class GradeSelectActivity extends AppCompatActivity implements AddGradeDialog.AddGradeListener {

    // Static Variables
    private static final String USER_COL = "USERS";
    private static final String SCHOOL_COL = "SCHOOLS";

    // Variables
    private String userName;
    private String schoolName;

    //The school object from the selected school @ main
    private School school;

    //Array to store all the grades
    private List<Grade> grades = new ArrayList<>();

    // FireBase FireStore database
    private FirebaseFirestore db;
    private CollectionReference schoolCollection;
    private DocumentReference userDoc;
    private DocumentReference schoolDoc;

    // View holders
    private RecyclerView resView;
    private RecyclerView.Adapter adapt;
    private RecyclerView.LayoutManager resViewLayMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_select);
        Bundle extras = getIntent().getExtras();
        schoolName = extras.getString("SCHOOL_NAME");
        userName = extras.getString("USER_NAME");

        // Initialize RecyclerView
        resView = findViewById(R.id.gradeResView);
        resView.setHasFixedSize(true);

        // Use a linear layout manager for the RecyclerView
        resViewLayMan = new LinearLayoutManager(this);
        resView.setLayoutManager(resViewLayMan);

        // Get the FireStore database
        db = FirebaseFirestore.getInstance();
        userDoc = db.collection(USER_COL).document(userName);
        schoolCollection = userDoc.collection(SCHOOL_COL);
        schoolDoc = schoolCollection.document(schoolName);
        Log.i("FIREBASE", "Loaded schoolDoc");
        getSchool();

        // Populate the list of grades
        initializeGradeList();

        // Set View Adapter
        adapt = new GradeAdapter(grades, this);
        resView.setAdapter(adapt);

        // Toolbar properties
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo: add code for grade adder popup
            }
        });
    }


    private void getSchool() {
        //DocumentReference schoolDoc = schoolCollection.document(skl);
//        schoolDoc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                School sk = documentSnapshot.toObject(School.class);
//                school = sk;
//            }
//        });
        schoolDoc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                Log.e("GET_SCHOOL", "onComplete is Called");
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    School sk = doc.toObject(School.class);
                    school = sk;
                }
            }
        });
        schoolDoc.get().addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("GET_SCHOOL", "Failed Hard");
            }
        });

    }

    private void initializeGradeList() {
        Log.i("GRADE_LIST", "Starting to Initialize Grade List");
        grades = school.getGrades();
    }

    // On Click Method for the add button
    public void addGrade(View view) {
        AddGradeDialog grdDialog = new AddGradeDialog();
        grdDialog.show(getSupportFragmentManager(), "newGrade");
    }

    @Override
    public void onDialogPositiveClick(int grade) {
        Grade g = new Grade(grade);
        grades.add(g);
        schoolDoc.update("grades", grades);
    }
}
