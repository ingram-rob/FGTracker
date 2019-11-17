package com.inurpocketapps.fgtracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddSchoolDialog.AddStudentListener {

    //Array to store all schools
    private List <School> schools = new ArrayList<>();
    private SchoolAdapter adapt = new SchoolAdapter(schools);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView resView = findViewById(R.id.mainResView);

        // Use a linear layout manager for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        resView.setLayoutManager(layoutManager);
        resView.setAdapter(adapt);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        String email = pref.getString("Email", "null");
        String userName = pref.getString("User", "null");
        //System.out.println(email);

    }

    @Override
    protected void onStop () {
        super.onStop();
        String email = "blabla@yahoo.com";
        String userName = "testUser";
        SharedPreferences pref = getApplicationContext().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("Email", email);
        edit.putString("User", userName);
        edit.apply();
    }

    //onClick method to add a new school.
    public void addSchool (View view){
        // Create Alert Dialog for School Name input
        AddSchoolDialog addStu = new AddSchoolDialog();
        addStu.show(getSupportFragmentManager(), "newSchool");
    }

    @Override
    public void onDialogPositiveClick(String schoolName) {
        School s = new School(schoolName);
        schools.add(s);
    }
}
