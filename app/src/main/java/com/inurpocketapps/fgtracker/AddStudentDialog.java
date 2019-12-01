package com.inurpocketapps.fgtracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

public class AddStudentDialog extends DialogFragment {
    private View studentView;

    public interface AddStudentListener {
        void onDialogPositiveClick (String first, String middle, String last);
    }

    private AddStudentListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (AddStudentDialog.AddStudentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement AddStudentListener");
        }
    }

    @Override
    public Dialog onCreateDialog (Bundle saveInstanceState) {
        AlertDialog.Builder cBuilderDialog = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        studentView = layoutInflater.inflate(R.layout.student_input_dialog, null);

        cBuilderDialog.setTitle("Input Student Name");
        cBuilderDialog.setView(studentView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText first = getDialog().findViewById(R.id.first);
                        EditText middle = getDialog().findViewById(R.id.middle);
                        EditText last = getDialog().findViewById(R.id.last);
                        String firstStr = first.getText().toString();
                        String middleStr = middle.getText().toString();
                        String lastStr = last.getText().toString();
                        listener.onDialogPositiveClick(firstStr, middleStr, lastStr);
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
