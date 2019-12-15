package com.inurpocketapps.fgtracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UpperTestAdapter extends RecyclerView.Adapter <UpperTestAdapter.UpperViewHolder> {

    // Variables
    private List<UpperBodyTest> dataSet;

    // Constructor
    UpperTestAdapter (List<UpperBodyTest> data) {
        this.dataSet = data;
    }

    @NonNull
    @Override
    public UpperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View tests = LayoutInflater.from(parent.getContext()).inflate(R.layout.upper_test_item, parent, false);
        return new UpperViewHolder(tests);
    }

    @Override
    public void onBindViewHolder(@NonNull UpperTestAdapter.UpperViewHolder holder, int position) {
        UpperBodyTest test = dataSet.get(position);
        holder.testDate.setText(test.getDate());
        holder.pushUp.setText(test.getPushUps());
        holder.pullUp.setText(test.getPullUps());
        holder.armHang.setText(test.getArmHang());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class UpperViewHolder extends RecyclerView.ViewHolder {
        public TextView testDate;
        public TextView pushUp;
        public TextView pullUp;
        public TextView armHang;

        public UpperViewHolder(@NonNull View itemView) {
            super(itemView);
            testDate = itemView.findViewById(R.id.resultDate);
            pushUp = itemView.findViewById(R.id.resultPushUp);
            pullUp = itemView.findViewById(R.id.resultPullUp);
            armHang = itemView.findViewById(R.id.resultArmHang);
        }
    }
}
