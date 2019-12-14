package com.inurpocketapps.fgtracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        AddSchoolDialog.AddStudentListener, AddUserDialog.AddUserListener {

    // Static Variables
    private static final String USER_COL = "USERS";
    private static final String SCHOOL_COL = "SCHOOLS";

    // Persistant variables
    private String email;
    private String userName;
    private boolean firstTimeUser;

    //Array to store all schools
    private List <School> schools = new ArrayList<>();

    // Firebase Authentication
    FirebaseAuth userAuth;

    // Firebase Firestore database
    private FirebaseFirestore db;
    private CollectionReference schoolColection;
    private DocumentReference userDoc;

    // View holders
    private RecyclerView resView;
    private RecyclerView.Adapter adapt;
    private RecyclerView.LayoutManager resViewLayMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        resView = findViewById(R.id.mainResView);
        resView.setHasFixedSize(true);

        // Use a linear layout manager for the RecyclerView
        resViewLayMan = new LinearLayoutManager(this);
        resView.setLayoutManager(resViewLayMan);

        // Get shared preferences
        SharedPreferences pref = getApplicationContext().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        email = pref.getString("Email", "null");
        userName = pref.getString("User", "null");
        firstTimeUser = pref.getBoolean("FirstTime", true);
        //System.out.println(email);

        firstTimeUser();

        // Set View Adapter
        adapt = new SchoolAdapter(schools, this, userName);
        resView.setAdapter(adapt);

        // Get the Firestore database
        db = FirebaseFirestore.getInstance();
        userDoc = db.collection(USER_COL).document(userName);
        schoolColection = userDoc.collection(SCHOOL_COL);

        // Initialize the school list from the database
        initializeSchoolList();

    }

    @Override
    protected void onStop () {
        super.onStop();
        //String email = "blabla@yahoo.com";
        //String userName = "testUser";
        SharedPreferences pref = getApplicationContext().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("Email", email);
        edit.putString("User", userName);
        edit.putBoolean("FirstTime", firstTimeUser);
        edit.apply();
    }

    // onClick method to add a new school.
    public void addSchool (View view){
        // Create Alert Dialog for School Name input
        AddSchoolDialog addStu = new AddSchoolDialog();
        addStu.show(getSupportFragmentManager(), "newSchool");
    }

    // Add school dialog positive button click event.
    @Override
    public void onDialogPositiveClick(String schoolName) {
        final School s = new School(schoolName);
        final DocumentReference newSchool = schoolColection.document(s.getName());
        newSchool.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()) { Log.d("FIREBASE","school already exists"); }
                    else {
                        newSchool.set(s).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("FIREBASE","successfully added new school");
                                schools.add(s);
                                adapt.notifyDataSetChanged();
                            }
                        })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("FIREBASE","failed to add new school");
                                    }
                                });
                    }
                }
                else { Log.d("FIREBASE","get failed"); }
            }
        });
    }

    private void initializeSchoolList() {
        schoolColection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        School sk = document.toObject(School.class);
                        schools.add(sk);
                        adapt.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    private void firstTimeUser() {
        if (firstTimeUser) {
            // Create Add User Dialog
            AddUserDialog addUser = new AddUserDialog();
            addUser.show(getSupportFragmentManager(), "newUser");
        }
    }

    // Add user Dialog positive button click event
    @Override
    public void onDialogPositiveClick(String user, String newEmail) {
        userName = user;
        email = newEmail;
        firstTimeUser = false;
    }
}
