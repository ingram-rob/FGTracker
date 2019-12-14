package com.inurpocketapps.fgtracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

public class InputDateDialog extends DialogFragment {
    private View view;

    public interface InputDateListener {
        void onDialogPositiveClick (Integer month, Integer day, Integer year);
    }

    private InputDateDialog.InputDateListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (InputDateDialog.InputDateListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement InputDateListener");
        }
    }

    @Override
    public Dialog onCreateDialog (Bundle saveInstanceState) {
        AlertDialog.Builder cBuilderDialog = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        view = layoutInflater.inflate(R.layout.input_date_dialog, null);

        //Get NumberPickers
        final NumberPicker monthPicker = view.findViewById(R.id.month);
        final NumberPicker dayPicker = view.findViewById(R.id.day);
        final NumberPicker yearPicker = view.findViewById(R.id.year);

        //set number picker limits
        monthPicker.setMaxValue(12);
        monthPicker.setMinValue(1);
        dayPicker.setMaxValue(31);
        dayPicker.setMinValue(1);
        yearPicker.setMaxValue(2050);
        yearPicker.setMinValue(1980);

        cBuilderDialog.setTitle("Input Date");
        cBuilderDialog.setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Integer month = monthPicker.getValue();
                        Integer day = dayPicker.getValue();
                        Integer year = yearPicker.getValue();
                        listener.onDialogPositiveClick(month, day, year);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing
                    }
                });
        return cBuilderDialog.create();
    }
}
