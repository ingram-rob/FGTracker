package com.inurpocketapps.fgtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FlexTestAdapter extends RecyclerView.Adapter <FlexTestAdapter.FlexViewHolder> {

    // Variables
    private List<FlexibilityTest> dataSet;
    //private Context cont;

    //Constructor
    FlexTestAdapter(List<FlexibilityTest> dataSet, Context cont) {
        this.dataSet = dataSet;
        //this.cont = cont;
    }

    @NonNull
    @Override
    public FlexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the Item Layout
        View tests = LayoutInflater.from(parent.getContext()).inflate(R.layout.flex_test_item, parent, false);
        return new FlexViewHolder(tests);
    }

    @Override
    public void onBindViewHolder(@NonNull FlexViewHolder holder, int position) {
        FlexibilityTest test = dataSet.get(position);
        holder.testDate.setText(test.getDate());
        holder.leftReach.setText(test.getSitAndReachLeft());
        holder.rightReach.setText(test.getSitAndReachRight());
        if (test.isLeftPass())
            holder.leftShoulder.setText("Pass");
        else
            holder.leftShoulder.setText("Fail");

        if (test.isRightPass())
            holder.rightShoulder.setText("Pass");
        else
            holder.rightShoulder.setText("Fail");
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class FlexViewHolder extends RecyclerView.ViewHolder {
        public TextView testDate;
        public TextView leftReach;
        public TextView rightReach;
        public TextView leftShoulder;
        public TextView rightShoulder;

        public FlexViewHolder(@NonNull View itemView) {
            super(itemView);
            testDate = itemView.findViewById(R.id.resultDate);
            leftReach = itemView.findViewById(R.id.sitLeftResult);
            rightReach = itemView.findViewById(R.id.sitRightResult);
            leftShoulder = itemView.findViewById(R.id.resultLeft);
            rightShoulder = itemView.findViewById(R.id.resultRight);
        }
    }
}
