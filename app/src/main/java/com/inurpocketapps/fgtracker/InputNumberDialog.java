package com.inurpocketapps.fgtracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

public class InputNumberDialog extends DialogFragment {
    private View view;
    private String targetField;
    private String unitStr = "";
    private TextView targetView;
    private Boolean isInteger;

    //non-default constructor
    public InputNumberDialog(TextView targetView, String targetField, Boolean isInteger) {
        this.targetField = targetField;
        this.targetView = targetView;
        this.isInteger = isInteger;
    }

    public InputNumberDialog(TextView targetView, String targetField, String unitStr, Boolean isInteger) {
        this.targetField = targetField;
        this.targetView = targetView;
        this.unitStr = unitStr;
        this.isInteger = isInteger;
    }

    public interface InputNumberListener {
        void onDialogPositiveClick (Float num, TextView targetView, String targetField, String unitStr, Boolean isInteger);
    }

    private InputNumberDialog.InputNumberListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (InputNumberDialog.InputNumberListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement InputNumberListener");
        }
    }

    @Override
    public Dialog onCreateDialog (Bundle saveInstanceState) {
        AlertDialog.Builder cBuilderDialog = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        view = layoutInflater.inflate(R.layout.number_input_dialog, null);

        cBuilderDialog.setTitle("Input Number");
        cBuilderDialog.setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText numberView = getDialog().findViewById(R.id.inputNumber);
                        Float num = Float.parseFloat(numberView.getText().toString());
                        listener.onDialogPositiveClick(num, targetView, targetField, unitStr, isInteger);
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
