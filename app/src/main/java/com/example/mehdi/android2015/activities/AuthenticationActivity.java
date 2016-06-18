package com.example.mehdi.android2015.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mehdi.android2015.R;
import com.example.mehdi.android2015.core.Auth;
import com.example.mehdi.android2015.services.Account;
import com.squareup.otto.Subscribe;

public class AuthenticationActivity extends BaseActivity {

    private Auth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        auth = application.getAuth();
        if(!auth.hasAuthToken()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }


        bus.post(new Account.LoginWithLocalTokenRequest(auth.getAuthToken()));
    }



    @Subscribe
    public void onLoginWithLocalToken(Account.LoginWithLocalTokenResponse response) {
        if(!response.didSucceed()) {
            Toast.makeText(this, "Please Login again", Toast.LENGTH_SHORT).show();
            auth.setAuthToken(null);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        startActivity(new Intent(this, MainActivity.class));
    }
}
