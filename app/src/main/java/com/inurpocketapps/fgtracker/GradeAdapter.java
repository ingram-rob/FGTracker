package com.inurpocketapps.fgtracker;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GradeAdapter extends RecyclerView.Adapter <SimpleViewHolder> {
    private List<Grade> dataSet;

    GradeAdapter (List <Grade> dataSet) {
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
        holder.textView.setText("Grade " + dataSet.get(position).getGradeNumber());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
