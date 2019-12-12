package com.inurpocketapps.fgtracker;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.widget.TextView;

public class StudentInfo extends AppCompatActivity {

    // Static Variables
    private static final String CLASS_COL = "CLASSES";
    private static final String SCHOOL_COL = "SCHOOLS";
    private static final String GRADE_COL = "Grades";
    private static final String STUDENT_COL = "STUDENTS";

    //Student object to hold data
    Student student;

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
    private DocumentReference studentDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);
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
        studentDoc = studentCollection.document(studentName);

        //Get student info views
        final TextView name = findViewById(R.id.nameVal);
        final TextView id = findViewById(R.id.idVal);
        final TextView age = findViewById(R.id.ageVal);
        final TextView birthdate = findViewById(R.id.birthdateVal);
        final TextView heightinches = findViewById(R.id.heightinchesVal);
        final TextView heightfeet = findViewById(R.id.heightfeetVal);
        final TextView weight = findViewById(R.id.weightVal);

        //Get student info from document
        studentDoc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        student = document.toObject(Student.class);

                        //fill views with data
                        Integer ageint = new Integer(student.getAge());
                        Integer heightinchesint = new Integer(student.getHeightInches());
                        Float heightfeetint = new Float(student.getHeightFeet());
                        name.setText(student.getName());
                        id.setText(student.getId());
                        age.setText(ageint.toString());
                        birthdate.setText(student.getBirthDate());
                        heightinches.setText(heightinchesint.toString());
                        heightfeet.setText(heightfeetint.toString());
                        weight.setText(student.getWeight() + "lb");
                    } else {
                        Log.e("FIREBASE", "Failed to get student info: ", task.getException());
                    }
                }
            }
        });
    }

}
