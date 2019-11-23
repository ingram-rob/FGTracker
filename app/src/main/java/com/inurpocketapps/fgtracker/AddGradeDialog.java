package com.inurpocketapps.fgtracker;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AddGradeDialog extends DialogFragment {

    private View grdView;
    private NumberPicker num;

    public interface AddGradeListener {
        void onDialogPositiveClick (int grade);
    }

    AddGradeListener gradeListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            gradeListener = (AddGradeListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement AddGradeListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        super.onCreateDialog(savedInstanceState);

        // Inflate custom layout
        LayoutInflater gDialogInflator = requireActivity().getLayoutInflater();
        grdView = gDialogInflator.inflate(R.layout.grade_input_dialog,null);

        // Build dialog
        AlertDialog.Builder gDialogBuilder = new AlertDialog.Builder(getActivity());
        gDialogBuilder.setView(grdView);
        gDialogBuilder.setTitle(R.string.add_grade_title);
        num = grdView.findViewById(R.id.gradePicker);
        num.setMaxValue(12);
        num.setMinValue(1);
        gDialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int grade = num.getValue();
                gradeListener.onDialogPositiveClick(grade);
            }
        });
        gDialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //todo: add button event if needed.
            }
        });

        return gDialogBuilder.create();
    }
}
