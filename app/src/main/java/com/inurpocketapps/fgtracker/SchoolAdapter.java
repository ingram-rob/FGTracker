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

public class SchoolAdapter extends RecyclerView.Adapter <SchoolAdapter.SchoolViewHolder> {
    private List <School> dataSet;
    private Context cont;
    private String username;

    SchoolAdapter (List <School> dataSet, Context cont, String user) {
        this.dataSet = dataSet;
        this.cont = cont;
        this.username = user;
    }

    /*@Override
    public SimpleViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        // create a new view
        TextView v = new TextView(parent.getContext());
        v.setHeight(150);
        v.setTextSize(25);
        v.setPadding(50, 25, 0, 0);
        SimpleViewHolder vh = new SimpleViewHolder(v);
        return vh;
    }*/

    @NonNull
    @Override
    public SchoolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
        View schools = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent, false);
        SchoolViewHolder vwHldr = new SchoolViewHolder(schools);
        return vwHldr;
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolViewHolder holder, final int position) {
        holder.text.setText(dataSet.get(position).getName());
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cont, GradeSelectActivity.class);
                Bundle extras = new Bundle();
                extras.putString("SCHOOL_NAME", dataSet.get(position).getName());
                extras.putString("USER_NAME", username);
                intent.putExtras(extras);
                cont.startActivity(intent);
            }
        });
    }

    public static class SchoolViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public SchoolViewHolder(View txt){
            super(txt);
            text = txt.findViewById(R.id.schoolName);
        }
    }


    /*@Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.textView.setText(dataSet.get(position).getName());

    }*/

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
