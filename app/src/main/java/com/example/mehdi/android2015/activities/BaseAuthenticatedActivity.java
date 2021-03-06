package com.example.mehdi.android2015.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public abstract class BaseAuthenticatedActivity extends BaseActivity {

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!application.getAuth().getUser().isLoggedIn()) {
            if(application.getAuth().hasAuthToken()) {
                startActivity(new Intent(this, AuthenticationActivity.class));
            } else {
                startActivity(new Intent(this, LoginActivity.class));
            }

            finish();
            return;
        }

        onAppCreate(savedInstanceState);
    }

    protected abstract void onAppCreate(Bundle savedInstanceState);
}
