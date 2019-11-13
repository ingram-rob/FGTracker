package com.inurpocketapps.fgtracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    // Dallin made this comment
    // Rob Made this comment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView resView = findViewById(R.id.mainResView);

        // Use a linear layout manager for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        resView.setLayoutManager(layoutManager);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        String email = pref.getString("Email", "null");
        System.out.println(email);
    }

    @Override
    protected void onStop () {
        super.onStop();
        String email = "blabla@yahoo.com";
        SharedPreferences pref = getApplicationContext().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("Email", email);
        edit.apply();
    }
}
