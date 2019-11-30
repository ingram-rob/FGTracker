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

public class AddClassDialog extends DialogFragment {

    private View classView;

    public interface AddClassroomListener {
        void onDialogPositiveClick (String text);
    }

    private AddClassroomListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (AddClassroomListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement AddClassroomListener");
        }
    }

    @Override
    public Dialog onCreateDialog (Bundle saveInstanceState) {
        AlertDialog.Builder cBuilderDialog = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        classView = layoutInflater.inflate(R.layout.class_input_dialog, null);

        cBuilderDialog.setTitle("Input classroom name");
        cBuilderDialog.setView(classView)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText txt = getDialog().findViewById(R.id.newClassroom);
                    String test = txt.getText().toString();
                    listener.onDialogPositiveClick(test);
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
