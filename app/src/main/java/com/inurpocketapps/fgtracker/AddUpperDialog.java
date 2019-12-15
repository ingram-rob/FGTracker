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

public class AddUpperDialog extends DialogFragment {

    private View upperView;

    public interface addUpperTestListener {
        void onAddUpperTestClick(String pushUps, String pullUps, String armHang);
    }

    addUpperTestListener uListener;

    @Override
    public void onAttach(Context cont) {
        super.onAttach(cont);

        try {
            uListener = (addUpperTestListener) cont;
        }catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "Must implement addUpperTestListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        // Inflate custom Layout
        LayoutInflater dInflater = requireActivity().getLayoutInflater();
        upperView = dInflater.inflate(R.layout.upper_input_dialog, null);

        // Build Dialog
        AlertDialog.Builder upperDialogBuilder = new AlertDialog.Builder(getActivity());
        upperDialogBuilder.setView(upperView);
        upperDialogBuilder.setTitle("Input Upper Body Results");
        upperDialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText pushUps = getDialog().findViewById(R.id.resultPushUp);
                String pUps = pushUps.getText().toString();

                EditText pullUps = getDialog().findViewById(R.id.resultPullUp);
                String puUps = pullUps.getText().toString();

                EditText aHang = getDialog().findViewById(R.id.resultArmHang);
                String armHang = aHang.getText().toString();

                uListener.onAddUpperTestClick(pUps, puUps, armHang);
            }
        });
        upperDialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do Nothing
            }
        });

        return upperDialogBuilder.create();
    }
}
