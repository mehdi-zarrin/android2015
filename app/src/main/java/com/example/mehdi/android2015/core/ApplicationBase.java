package com.example.mehdi.android2015.core;

import android.app.Application;

public class ApplicationBase extends Application {

    private Auth auth;

    @Override
    public void onCreate() {
        super.onCreate();
        this.auth = new Auth(this);
    }

    public Auth getAuth() {
        return auth;
    }
}
