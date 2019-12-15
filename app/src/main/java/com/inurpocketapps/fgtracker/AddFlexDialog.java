package com.inurpocketapps.fgtracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.fragment.app.DialogFragment;

public class AddFlexDialog extends DialogFragment {

    private View flexView;

    public interface addFlexTestListener {
        void onAddFlexTestClick(float lReach, float rReach, boolean lPass, boolean rPass);
    }

    addFlexTestListener fListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            fListener = (addFlexTestListener) context;
        }catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "Must implement addFlexTestListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        // Inflate custom Layout
        LayoutInflater dInflater = requireActivity().getLayoutInflater();
        flexView = dInflater.inflate(R.layout.flex_input_dialog, null);

        // Build dialog
        AlertDialog.Builder flexDialogBuilder = new AlertDialog.Builder(getActivity());
        flexDialogBuilder.setView(flexView);
        flexDialogBuilder.setTitle("Input Flexibility Results");
        flexDialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText leftStretchResult = getDialog().findViewById(R.id.editText);
                String leftSResult = leftStretchResult.getText().toString();
                float left = Float.valueOf(leftSResult);

                EditText rightStretchResult = getDialog().findViewById(R.id.editText2);
                String rightSResult = rightStretchResult.getText().toString();
                float right = Float.valueOf(rightSResult);

                RadioGroup leftRadios = getDialog().findViewById(R.id.leftRadioGroup);
                RadioGroup rightRadios = getDialog().findViewById(R.id.rightRadioGroup);
                boolean leftPass = false;
                boolean rightPass = false;

                if (leftRadios.getCheckedRadioButtonId() == R.id.leftRadioPass)
                    leftPass = true;

                if (rightRadios.getCheckedRadioButtonId() == R.id.rightRadioPass)
                    rightPass = true;

                fListener.onAddFlexTestClick(left, right, leftPass, rightPass);
            }
        });
        flexDialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing.
            }
        });

        return flexDialogBuilder.create();
    }

}
