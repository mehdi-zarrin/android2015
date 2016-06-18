package com.example.mehdi.android2015.dialogs;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mehdi.android2015.R;
import com.example.mehdi.android2015.services.Account;
import com.squareup.otto.Subscribe;

public class ChangePasswordDialog extends BaseDialogFragment implements View.OnClickListener {
    private EditText currentPassword;
    private EditText newPassword;
    private EditText confirmNewPassword;
    private Dialog progressDialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_change_password, null, false);

        currentPassword = (EditText) dialogView.findViewById(R.id.dialog_change_password_currentPassword);
        newPassword = (EditText) dialogView.findViewById(R.id.dialog_change_password_newPassword);
        confirmNewPassword = (EditText) dialogView.findViewById(R.id.dialog_change_password_confirmNewPassword);

        if(!application.getAuth().getUser().isHasPassword()) {
            currentPassword.setVisibility(View.GONE);
        }

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                                        .setView(dialogView)
                                        .setPositiveButton("Update" , null)
                                        .setNegativeButton("Cancel" , null)
                                        .setTitle("Change Password")
                                        .show();
        alertDialog.getButton(alertDialog.BUTTON_POSITIVE).setOnClickListener(this);
        return alertDialog;
    }


    @Override
    public void onClick(View v) {
        // TODO : send new password to server
        progressDialog = new ProgressDialog.Builder(getActivity())
                .setTitle("changing password")
                .setCancelable(false)
                .show();
        bus.post(new Account.changePasswordRequest(
                currentPassword.getText().toString(),
                newPassword.getText().toString(),
                confirmNewPassword.getText().toString()));


    }

    @Subscribe
    public void onPasswordUpdated(Account.changePasswordResponse response) {

        progressDialog.dismiss();
        progressDialog = null;

        if(!response.didSucceed()) {
            response.showErrorToast(getActivity());
        } else {
            Toast.makeText(getActivity(), "Password updated!", Toast.LENGTH_LONG).show();
            dismiss();
            application.getAuth().getUser().setHasPassword(true);
            return;
        }


        currentPassword.setError(response.getPropertyError("currentPassword"));
        newPassword.setError(response.getPropertyError("newPassword"));
        confirmNewPassword.setError(response.getPropertyError("confirmNewPassword"));

        response.showErrorToast(getActivity());
    }
}
