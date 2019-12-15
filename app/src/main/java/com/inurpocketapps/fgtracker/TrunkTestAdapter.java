package com.inurpocketapps.fgtracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TrunkTestAdapter extends RecyclerView.Adapter <TrunkTestAdapter.TrunkViewHolder> {

    // Variables
    private List<TrunkTest> dataSet;

    // Constructor
    TrunkTestAdapter (List<TrunkTest> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public TrunkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the Item Layout
        View tests = LayoutInflater.from(parent.getContext()).inflate(R.layout.trunk_test_item, parent, false);
        return new TrunkViewHolder(tests);
    }

    @Override
    public void onBindViewHolder(@NonNull TrunkTestAdapter.TrunkViewHolder holder, int position) {
        TrunkTest test = dataSet.get(position);
        holder.testDate.setText(test.getDate());
        holder.testResult.setText(test.getTrunkLift());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class TrunkViewHolder extends RecyclerView.ViewHolder {
        public TextView testDate;
        public TextView testResult;

        public TrunkViewHolder(@NonNull View itemView) {
            super(itemView);
            testDate = itemView.findViewById(R.id.resultDate);
            testResult = itemView.findViewById(R.id.trunkResult);
        }
    }
}
