package com.inurpocketapps.fgtracker;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AddSchoolDialog extends DialogFragment {

    private View sView;

    public interface AddStudentListener {
        public void onDialogPositiveClick(String schoolName);
    }

    AddStudentListener imListening;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            imListening = (AddStudentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement AddStudentListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        super.onCreateDialog(savedInstanceState);

        // Inflate custom layout
        LayoutInflater sDialogInflator = requireActivity().getLayoutInflater();
        sView = sDialogInflator.inflate(R.layout.school_input_dialog,null);

        // Build dialog
        AlertDialog.Builder sDialogBuilder = new AlertDialog.Builder(getActivity());
        sDialogBuilder.setView(sView);
        sDialogBuilder.setTitle(R.string.add_school_title);
        sDialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText txt = getDialog().findViewById(R.id.newSchoolName);
                String test = txt.getText().toString();
                imListening.onDialogPositiveClick(test);
            }
        });
        sDialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //todo: add button event if needed.
            }
        });

        return sDialogBuilder.create();
    }
}
