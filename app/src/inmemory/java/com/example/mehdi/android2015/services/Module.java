package com.example.mehdi.android2015.services;

import android.util.Log;

import com.example.mehdi.android2015.core.ApplicationBase;

public class Module {
    public static void register(ApplicationBase application) {
        new InMemoryAccountService(application);
        new InMemoryContactService(application);
        new InMemoryMessageService(application);
    }
}