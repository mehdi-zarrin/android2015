package com.example.mehdi.android2015.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.mehdi.android2015.R;
import com.example.mehdi.android2015.views.MainNavDrawer;

public class MainActivity extends BaseAuthenticatedActivity {
    Toolbar toolbar;
    @Override
    protected void onAppCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        Log.e("DDDD" , "main activity!");
        setNavDrawer(new MainNavDrawer(this));
    }
}
