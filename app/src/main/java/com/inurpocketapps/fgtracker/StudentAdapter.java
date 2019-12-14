package com.inurpocketapps.fgtracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter <StudentAdapter.StudentViewHolder> {
    private List<Student> dataSet;
    private Context context;
    private Bundle extras;

    StudentAdapter (List <Student> dataSet, Context context, Bundle extras) {
        this.context = context;
        this.extras = extras;
        this.dataSet = dataSet;
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public StudentViewHolder(View txt){
            super(txt);
            text = txt.findViewById(R.id.studentName);
        }
    }

    @Override
    public StudentViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        // create a new view
        View students = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_item, parent, false);
        StudentViewHolder vh = new StudentViewHolder(students);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        final String studentName = dataSet.get(position).getName();
        holder.text.setText(studentName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StudentInfo.class);
                intent.putExtra("STUDENT_NAME", studentName);
                intent.putExtra("CLASS_NAME", extras.getString("CLASS_NAME"));
                intent.putExtra("GRADE_NUMBER", extras.getInt("GRADE_NUMBER"));
                intent.putExtra("SCHOOL_NAME", extras.getString("SCHOOL_NAME"));
                intent.putExtra("USER_NAME", extras.getString("USER_NAME"));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
