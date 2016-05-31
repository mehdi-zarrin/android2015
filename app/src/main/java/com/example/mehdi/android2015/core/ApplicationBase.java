package com.example.mehdi.android2015.core;

import android.app.Application;

import com.example.mehdi.android2015.services.Module;
import com.squareup.otto.Bus;

public class ApplicationBase extends Application {

    private Auth auth;
    private Bus bus = new Bus();

    @Override
    public void onCreate() {
        super.onCreate();
        this.auth = new Auth(this);
        Module.register(this);
    }

    public Auth getAuth() {
        return auth;
    }

    public Bus getBus() {
        return bus;
    }
}
