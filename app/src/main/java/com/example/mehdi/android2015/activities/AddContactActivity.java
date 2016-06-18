package com.example.mehdi.android2015.activities;

import android.os.Bundle;

import com.example.mehdi.android2015.R;

public class AddContactActivity extends BaseAuthenticatedActivity {

    public static final String RESULT_CONTACT = "RESULT_CONTACT";
    @Override
    protected void onAppCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_contact);
    }
}
