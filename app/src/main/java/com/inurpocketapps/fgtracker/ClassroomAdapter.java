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

import javax.annotation.Nonnull;

public class ClassroomAdapter extends RecyclerView.Adapter <ClassroomAdapter.ClassroomViewHolder> {
    private List<Classroom> dataSet;
    private Context context;
    private Bundle extras;

    ClassroomAdapter (List <Classroom> dataSet, Context context, Bundle extras) {
        this.context = context;
        this.dataSet = dataSet;
        this.extras = extras;
    }

    public static class ClassroomViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public ClassroomViewHolder(View txt){
            super(txt);
            text = txt.findViewById(R.id.classroomName);
        }
    }

    @Override
    public ClassroomViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        // create a new view
        View classrooms = LayoutInflater.from(parent.getContext()).inflate(R.layout.classroom_list_item, parent, false);
        ClassroomViewHolder vh = new ClassroomViewHolder(classrooms);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ClassroomViewHolder holder, int position) {
        final String classroomName = dataSet.get(position).getName();
        holder.text.setText(classroomName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StudentActivity.class);
                intent.putExtra("CLASS_NAME", classroomName);
                //Pass info from previous activity to new activity
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
