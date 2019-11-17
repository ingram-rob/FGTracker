package com.inurpocketapps.fgtracker;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
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
    private CollectionReference schoolColection;
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
        System.out.println(userName);

        // Initialize RecyclerView
        resView = findViewById(R.id.gradeResView);
        resView.setHasFixedSize(true);

        // Use a linear layout manager for the RecyclerView
        resViewLayMan = new LinearLayoutManager(this);
        resView.setLayoutManager(resViewLayMan);

        // Get the FireStore database
        db = FirebaseFirestore.getInstance();
        userDoc = db.collection(USER_COL).document(userName);
        schoolColection = userDoc.collection(SCHOOL_COL);
        schoolDoc = schoolColection.document(schoolName);
        getSchool(schoolName);

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void getSchool(String skl) {
        DocumentReference schoolDoc = schoolColection.document(skl);
        schoolDoc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot doc) {
                School sk = doc.toObject(School.class);
                school = sk;
            }
        });
    }

    private void initializeGradeList() {
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
