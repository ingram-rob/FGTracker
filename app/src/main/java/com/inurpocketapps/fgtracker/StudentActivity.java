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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends AppCompatActivity implements AddStudentDialog.AddStudentListener {

    // Static Variables
    private static final String CLASS_COL = "CLASSES";
    private static final String SCHOOL_COL = "SCHOOLS";
    private static final String GRADE_COL = "Grades";
    private static final String STUDENT_COL = "STUDENTS";

    // Persistant variables
    private String email;
    private String userName;
    private String schoolName;
    private int gradeNumber;
    private String classroomName;

    //Array to store all schools
    private List<Student> students = new ArrayList<>();

    // Firebase Firestore database
    private FirebaseFirestore db;
    private CollectionReference schoolCollection;
    private DocumentReference userDoc;
    private CollectionReference gradeCollection;
    private CollectionReference classroomCollection;
    private CollectionReference studentCollection;

    // View holders
    private RecyclerView resView;
    private RecyclerView.Adapter adapt;
    private RecyclerView.LayoutManager resViewLayMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_select);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        schoolName = extras.getString("SCHOOL_NAME");
        userName = extras.getString("USER_NAME");
        gradeNumber = extras.getInt("GRADE_NUMBER");
        classroomName = extras.getString("CLASS_NAME");

        //Set up recycler view
        resView = findViewById(R.id.studentResView);
        resView.setHasFixedSize(true);

        // Use a linear layout manager for the RecyclerView
        resViewLayMan = new LinearLayoutManager(this);
        resView.setLayoutManager(resViewLayMan);

        // Set View Adapter
        adapt = new StudentAdapter(students, this, extras);
        resView.setAdapter(adapt);

        //Get Database
        db = FirebaseFirestore.getInstance();
        userDoc = db.collection("USERS").document(userName);
        schoolCollection = userDoc.collection(SCHOOL_COL);
        gradeCollection = schoolCollection.document(schoolName).collection(GRADE_COL);
        classroomCollection = gradeCollection.document("Grade " + gradeNumber).collection(CLASS_COL);
        studentCollection = classroomCollection.document(classroomName).collection(STUDENT_COL);

        //Get all students in the collection
        studentCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        students.add(document.toObject(Student.class));
                    }
                    adapt.notifyDataSetChanged();
                }
                else {
                    Log.e("FIREBASE","Failed to get Students from collection");
                }
            }
        });
    }

    public void addStudent(View v) {
        AddStudentDialog dialog = new AddStudentDialog();
        dialog.show(getSupportFragmentManager(), "addStudent");
    }

    @Override
    public void onDialogPositiveClick (String first, String middle, String last) {
        Student s = new Student(first, middle, last);
        students.add(s);
        adapt.notifyDataSetChanged();
        DocumentReference newStudent = studentCollection.document(first + " " + middle + " " + last);
        newStudent.set(s).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.e("FIREBASE", "Successfully added new student");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("FIREBASE", "Failed to add new student", e);
                    }
                });
    }
}
