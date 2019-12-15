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

public class AddTrunkDialog extends DialogFragment {

    private View trunkView;

    public interface addTrunkTestListener {
        void onAddTruckTestClick(String result);
    }

    addTrunkTestListener tListener;

    @Override
    public void onAttach(Context cont) {
        super.onAttach(cont);

        try {
            tListener = (addTrunkTestListener) cont;
        }catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "Must implement addFlexTestListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        super.onCreateDialog(savedInstanceState);

        // Inflate custom Layout
        LayoutInflater dInflater = requireActivity().getLayoutInflater();
        trunkView = dInflater.inflate(R.layout.trunk_input_dialog, null);

        // Build dialog
        AlertDialog.Builder trunkDialogBuilder = new AlertDialog.Builder(getActivity());
        trunkDialogBuilder.setView(trunkView);
        trunkDialogBuilder.setTitle("Input Trunk Lift Results");
        trunkDialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText trunkLiftResult = getDialog().findViewById(R.id.trunkResult);
                String liftResult = trunkLiftResult.getText().toString();

                tListener.onAddTruckTestClick(liftResult);
            }
        });
        trunkDialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do Nothing
            }
        });

        return trunkDialogBuilder.create();
    }
}
