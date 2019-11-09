package com.inurpocketapps.fgtracker;

import android.os.Bundle;

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
    }
}
