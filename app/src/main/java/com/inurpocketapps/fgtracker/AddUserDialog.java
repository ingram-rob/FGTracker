package com.inurpocketapps.fgtracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class AddUserDialog extends DialogFragment {

    private View userView;

    public interface AddUserListener {
        void onDialogPositiveClick(String user, String email);
    }

    AddUserListener userListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            userListener = (AddUserListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement AddStudentListener");
        }
    }


    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState) {

        super.onCreateDialog(savedInstanceState);

        // Inflate custom layout
        LayoutInflater userDialogInflater = requireActivity().getLayoutInflater();
        userView = userDialogInflater.inflate(R.layout.user_input_dialog, null);

        // Build dialog
        AlertDialog.Builder userDialogBuilder = new AlertDialog.Builder(getActivity());
        userDialogBuilder.setView(userView);
        userDialogBuilder.setTitle(R.string.add_user_title);
        userDialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText etName = getDialog().findViewById(R.id.newUserName);
                EditText etEmail = getDialog().findViewById(R.id.newUserEmail);
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                userListener.onDialogPositiveClick(name, email);
            }
        });
        userDialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.e("Add User Cancelled", "Canceled User input");
            }
        });

        return userDialogBuilder.create();
    }
}
