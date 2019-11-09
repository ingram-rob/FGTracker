package com.inurpocketapps.fgtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomListAdapter extends RecyclerView.Adapter {
    @NonNull
    ArrayList schoolNames;
    Context context;

    public  CustomListAdapter (Context cont, ArrayList sNames)
    {
        context = cont;
        schoolNames = sNames;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public MyViewHolder(View txt){
            super(txt);
            text = (TextView) txt.findViewById(R.id.schoolName);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Inflate the item layout
        View schools = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent, false);
        MyViewHolder vwHldr = new MyViewHolder(schools);

        return vwHldr;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.text.setText(schoolNames.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return schoolNames.size();
    }
}
