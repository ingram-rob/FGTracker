package com.inurpocketapps.fgtracker;


import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class SimpleViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public SimpleViewHolder(TextView v) {
        super(v);
        textView = v;
    }
}
