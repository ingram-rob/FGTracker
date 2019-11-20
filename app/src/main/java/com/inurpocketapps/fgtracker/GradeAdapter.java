package com.inurpocketapps.fgtracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GradeAdapter extends RecyclerView.Adapter <GradeAdapter.GradeViewHolder> {
    private List<Grade> dataSet;
    private Context context;

    GradeAdapter (List <Grade> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    public static class GradeViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public GradeViewHolder(View txt){
            super(txt);
            text = txt.findViewById(R.id.schoolName);
        }
    }

    @Override
    public GradeViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        /*// create a new view
        TextView v = new TextView(parent.getContext());
        v.setHeight(150);
        v.setTextSize(25);
        v.setPadding(50, 25, 0, 0);*/
        View grades = LayoutInflater.from(parent.getContext()).inflate(R.layout.grade_list_item, parent, false);
        GradeViewHolder vh = new GradeViewHolder(grades);
        return vh;
    }

    @Override
    public void onBindViewHolder(GradeViewHolder holder, final int position) {
        holder.text.setText("Grade " + dataSet.get(position).getGradeNumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ClassActivity.class);
                intent.putExtra("GRADE_NUMBER", dataSet.get(position).getGradeNumber());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
