package com.example.mehdi.android2015.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mehdi.android2015.R;
import com.example.mehdi.android2015.fragments.LoginFragment;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final int REQUEST_NORROW_LOGIN = 1;
    private static final int REQUEST_REGISTER = 2;
    private View loginButton;
    private View registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.activity_login_login);
        registerButton = findViewById(R.id.activity_login_register);

        if(loginButton != null) {
            loginButton.setOnClickListener(this);
        }

        registerButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v == loginButton) {
            startActivityForResult(new Intent(this, LoginNarrowActivity.class), REQUEST_NORROW_LOGIN);
        } else if(v == registerButton) {
            startActivityForResult(new Intent(this, RegisterActivity.class), REQUEST_REGISTER);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_OK)
            return;

        if(requestCode == REQUEST_NORROW_LOGIN || requestCode == REQUEST_REGISTER)
            finishLogin();
    }

    private void finishLogin() {
//        application.getAuth().getUser().setDisplayName("Mehdi Zarrin");
        Log.e("DDDD" , "Login has been finished !");
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
