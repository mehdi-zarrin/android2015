package com.example.mehdi.android2015.activities;

import android.os.Bundle;
import android.util.Log;

import com.example.mehdi.android2015.R;
import com.example.mehdi.android2015.events.LoginEvent;
import com.example.mehdi.android2015.fragments.LoginFragment;
import com.squareup.otto.Subscribe;

public class LoginNarrowActivity extends BaseActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_narrow);
    }
//
//    @Override
//    public void onLoggedIn() {
//        setResult(RESULT_OK);
//        finish();
//    }

    @Subscribe
    public void onLogin(LoginEvent event) {
        Log.e("DDDD" , "finish login !");
        setResult(RESULT_OK);
        finish();
    }
}
