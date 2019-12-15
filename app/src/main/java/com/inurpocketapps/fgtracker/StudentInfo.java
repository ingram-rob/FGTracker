package com.inurpocketapps.fgtracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class StudentInfo extends AppCompatActivity implements AddStudentDialog.AddStudentListener, InputIdDialog.InputIdListener,
    InputNumberDialog.InputNumberListener, InputDateDialog.InputDateListener {

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

    public void changeName(View v) {
        AddStudentDialog dialog = new AddStudentDialog();
        dialog.show(getSupportFragmentManager(), "Change Name");
    }

    public void changeId(View v) {
        InputIdDialog dialog = new InputIdDialog();
        dialog.show(getSupportFragmentManager(), "Change Id");
    }

    public void changeAge(View v) {
        TextView targetView = findViewById(R.id.ageVal);
        InputNumberDialog dialog = new InputNumberDialog(targetView, "age", true);
        dialog.show(getSupportFragmentManager(), "Change Age");
    }

    public void changeHeightInches(View v) {
        TextView targetView = findViewById(R.id.heightinchesVal);
        InputNumberDialog dialog = new InputNumberDialog(targetView, "heightInches", true);
        dialog.show(getSupportFragmentManager(), "Change Height in Inches");
    }

    public void changeHeightFeet(View v) {
        TextView targetView = findViewById(R.id.heightfeetVal);
        InputNumberDialog dialog = new InputNumberDialog(targetView, "heightFeet", false);
        dialog.show(getSupportFragmentManager(), "Change Height in Feet");
    }

    public void changeWeight(View v) {
        TextView targetView = findViewById(R.id.weightVal);
        InputNumberDialog dialog = new InputNumberDialog(targetView, "weight", "lb", false);
        dialog.show(getSupportFragmentManager(), "Change Weight");
    }

    public void changeBirthDate(View v) {
        InputDateDialog dialog = new InputDateDialog();
        dialog.show(getSupportFragmentManager(), "Change Birth Date");
    }

    public void startAbdominalActivity (View v) {

    }

    public void startAerobicActivity (View v) {
        Intent aerobic = new Intent(this, AerobicActivity.class);
        Bundle extras = new Bundle();
        extras.putString("SCHOOL_NAME", schoolName);
        extras.putString("USER_NAME", userName);
        extras.putInt("GRADE_NUMBER", gradeNumber);
        extras.putString("CLASS_NAME", classroomName);
        extras.putString("STUDENT_NAME", studentName);
        aerobic.putExtras(extras);
        this.startActivity(aerobic);
    }

    public void startBodyCompActivity (View v) {

    }

    public void startFlexibilityActivity (View v) {
        Intent stretch = new Intent(this, FlexActivity.class);
        Bundle extras = new Bundle();
        extras.putString("SCHOOL_NAME", schoolName);
        extras.putString("USER_NAME", userName);
        extras.putInt("GRADE_NUMBER", gradeNumber);
        extras.putString("CLASS_NAME", classroomName);
        extras.putString("STUDENT_NAME", studentName);
        stretch.putExtras(extras);
        this.startActivity(stretch);
    }

    public void startTrunkActivity (View v) {

    }

    public void startUpperBodyActivity (View v) {

    }

    //AddStudentDialog overload
    @Override
    public void onDialogPositiveClick (final String first, final String middle, final String last) {
        final Student student = new Student(first, middle, last);
        student.setFirst(first);
        student.setMiddle(middle);
        student.setLast(last);

        //get data from current student
        studentDoc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    DocumentSnapshot currentStudent = task.getResult();
                    if (currentStudent.exists()) {
                        String id = currentStudent.getString("id");
                        String birthdate = currentStudent.getString("birthDate");
                        Integer age = currentStudent.get("age", Integer.class);
                        Integer heightinches = currentStudent.get("heightInches", Integer.class);
                        Float heightfeet = currentStudent.get("heightFeet", Float.class);
                        Integer weight = currentStudent.get("weight", Integer.class);

                        student.setId(id);
                        student.setAge(age);
                        student.setHeightInches(heightinches);
                        student.setHeightFeet(heightfeet);
                        student.setWeight(weight);
                        student.setBirthDate(birthdate);

                        //Create new student
                        DocumentReference newStudent = studentCollection.document(student.getName());
                        newStudent.set(student).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.e("FIREBASE", "Successfully set student " + student.getName());

                                //Delete old student
                                studentDoc.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.e("FIREBASE", "Old student successfully deleted");
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.e("FIREBASE", "Failed to delete old student", e);
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e("FIREBASE", "Failed to set student " + student.getName(), e);
                            }
                        });
                    }
                }
            }
        });
    }

    //InputIdDialog overload
    @Override
    public void onDialogPositiveClick(Integer num) {
        TextView idView = findViewById(R.id.idVal);
        String idStr = Integer.toString(num);
        idView.setText(idStr);

        //Change targeted field in document to value
        studentDoc.update("id", idStr).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.e("FIREBASE", "Id successfully changed");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("FIREBASE", "Failed to change id");
            }
        });
    }

    //InputNumberDialog overload
    @Override
    public void onDialogPositiveClick(Float num, TextView targetView, final String targetField, String unitStr, Boolean isInteger) {
        if (isInteger) {
            //Convert the float to an int.
            Integer intNum = (int) num.floatValue();

            //Change target view
            targetView.setText(intNum + unitStr);

            //Change targeted field in document to num
            studentDoc.update(targetField, intNum).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.e("FIREBASE", targetField + " successfully changed");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("FIREBASE", "Failed to change " + targetField + " in document");
                }
            });
        } else {
            //Change target view
            targetView.setText(num + unitStr);

            //Change targeted field in document to num
            studentDoc.update(targetField, num).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.e("FIREBASE", targetField + " successfully changed");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("FIREBASE", "Failed to change " + targetField + " in document");
                }
            });
        }
    }

    //InputDateDialog overload
    @Override
    public void onDialogPositiveClick(Integer month, Integer day, Integer year) {
        String birthdate = month + "/" + day + "/" + year;
        TextView birthdateView = findViewById(R.id.birthdateVal);
        birthdateView.setText(birthdate);

        //Update document field
        studentDoc.update("birthDate", birthdate).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.e("FIREBASE", "Birthdate successfully changed");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("FIREBASE", "Failed to change birthdate in document");
            }
        });
    }
}

