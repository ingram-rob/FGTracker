package com.inurpocketapps.fgtracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SchoolAdapter extends RecyclerView.Adapter <SimpleViewHolder> {
    private List <School> dataSet;

    SchoolAdapter (List <School> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        // create a new view
        TextView v = new TextView(parent.getContext());
        v.setHeight(150);
        v.setTextSize(25);
        v.setPadding(50, 25, 0, 0);
        SimpleViewHolder vh = new SimpleViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.textView.setText(dataSet.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
