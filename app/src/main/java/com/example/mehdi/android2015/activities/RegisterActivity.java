package com.example.mehdi.android2015.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.mehdi.android2015.R;


public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText usernameText, emailText, passwordText;
    private Button registerSubmit;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        usernameText = (EditText) findViewById(R.id.activity_register_username);
        emailText = (EditText) findViewById(R.id.activity_register_email);
        passwordText = (EditText) findViewById(R.id.activity_register_password);
        registerSubmit = (Button) findViewById(R.id.activity_register_submit);
        progressBar = (ProgressBar) findViewById(R.id.activity_register_progressBar);

        registerSubmit.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        application.getAuth().getUser().setLoggedIn(true);
        setResult(RESULT_OK);
        finish();
    }
}
