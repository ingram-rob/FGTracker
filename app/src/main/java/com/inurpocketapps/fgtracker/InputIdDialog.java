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

public class InputIdDialog extends DialogFragment {
    private View view;

    public interface InputIdListener {
        void onDialogPositiveClick (Integer num);
    }

    private InputIdDialog.InputIdListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (InputIdDialog.InputIdListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement InputNumberListener");
        }
    }

    @Override
    public Dialog onCreateDialog (Bundle saveInstanceState) {
        AlertDialog.Builder cBuilderDialog = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        view = layoutInflater.inflate(R.layout.number_input_dialog, null);

        cBuilderDialog.setTitle("Input Id");
        cBuilderDialog.setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText numberView = getDialog().findViewById(R.id.inputNumber);
                        Integer num = Integer.parseInt(numberView.getText().toString());
                        listener.onDialogPositiveClick(num);
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
