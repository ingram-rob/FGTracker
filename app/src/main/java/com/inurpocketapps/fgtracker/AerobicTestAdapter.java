package com.inurpocketapps.fgtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AerobicTestAdapter extends RecyclerView.Adapter <AerobicTestAdapter.AerobicViewHolder> {
    // Variables
    private List<AerobicTest> dataSet;

    //Constructor
    AerobicTestAdapter(List<AerobicTest> dataSet, Context cont) {
        this.dataSet = dataSet;
        //this.cont = cont;
    }

    @NonNull
    @Override
    public AerobicTestAdapter.AerobicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the Item Layout
        View tests = LayoutInflater.from(parent.getContext()).inflate(R.layout.aer_test_item, parent, false);
        return new AerobicTestAdapter.AerobicViewHolder(tests);
    }

    @Override
    public void onBindViewHolder(@NonNull AerobicTestAdapter.AerobicViewHolder holder, int position) {
        AerobicTest test = dataSet.get(position);
        holder.testDate.setText(test.getDate());
        holder.minRunVal.setText(test.getMileMin());
        holder.secRunVal.setText(test.getMileSec());
        holder.lapVal.setText(test.getPacerLaps());
        holder.minWalkVal.setText(test.getWalkMin());
        holder.secWalkVal.setText(test.getWalkSec());
        holder.heartRateVal.setText(test.getWalkPulse());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class AerobicViewHolder extends RecyclerView.ViewHolder {
        public TextView testDate;
        public TextView minRunVal;
        public TextView secRunVal;
        public TextView lapVal;
        public TextView minWalkVal;
        public TextView secWalkVal;
        public TextView heartRateVal;

        public AerobicViewHolder(@NonNull View itemView) {
            super(itemView);
            testDate = itemView.findViewById(R.id.dateVal);
            minRunVal = itemView.findViewById(R.id.minValRun);
            secRunVal = itemView.findViewById(R.id.secValRun);
            lapVal = itemView.findViewById(R.id.lapVal);
            minWalkVal = itemView.findViewById(R.id.minValWalk);
            secWalkVal = itemView.findViewById(R.id.secValWalk);
            heartRateVal = itemView.findViewById(R.id.heartRateVal);
        }
    }
}
