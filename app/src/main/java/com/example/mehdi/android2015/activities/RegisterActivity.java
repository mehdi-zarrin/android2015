package com.example.mehdi.android2015.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.mehdi.android2015.R;
import com.example.mehdi.android2015.services.Account;
import com.squareup.otto.Subscribe;


public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText usernameText, emailText, passwordText;
    private Button registerSubmit;
    private ProgressBar progressBar;
    private String RegisterButtonText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        usernameText = (EditText) findViewById(R.id.activity_register_username);
        emailText = (EditText) findViewById(R.id.activity_register_email);
        passwordText = (EditText) findViewById(R.id.activity_register_password);
        registerSubmit = (Button) findViewById(R.id.activity_register_submit);
        progressBar = (ProgressBar) findViewById(R.id.activity_register_progressBar);

        RegisterButtonText = registerSubmit.getText().toString();

        registerSubmit.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        usernameText.setEnabled(false);
        passwordText.setEnabled(false);
        emailText.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        registerSubmit.setText("");

        bus.post(new Account.RegisterRequest(
                usernameText.getText().toString(),
                emailText.getText().toString(),
                passwordText.getText().toString()
        ));
    }

    @Subscribe
    public void registerResponse(Account.RegisterResponse response) {
        onRegisterResponse(response);
    }

    private void onRegisterResponse(Account.RegisterResponse response) {

        if(response.didSucceed()) {
            setResult(RESULT_OK);
            finish();
            return;
        }

        response.showErrorToast(this);
        usernameText.setError(response.getPropertyError("username"));
        emailText.setError(response.getPropertyError("email"));
        passwordText.setError(response.getPropertyError("password"));


        usernameText.setEnabled(true);
        passwordText.setEnabled(true);
        emailText.setEnabled(true);
        progressBar.setVisibility(View.GONE);
        registerSubmit.setText(RegisterButtonText);



    }
}
