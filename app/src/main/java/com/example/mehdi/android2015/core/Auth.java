package com.example.mehdi.android2015.core;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.mehdi.android2015.activities.LoginActivity;

public class Auth {

    private static final String AUTH_PREFRENCES = "AUTH_PREFRENCES";
    private static final String AUTH_PREFRENCES_TOKEN = "AUTH_PREFRENCES_TOKEN";
    private final Context context;
    private User user;
    private final SharedPreferences preferences;
    private String authToken;

    public Auth(Context context) {
        this.context = context;
        user = new User();
        preferences = context.getSharedPreferences(AUTH_PREFRENCES, Context.MODE_PRIVATE);
        authToken = preferences.getString(AUTH_PREFRENCES_TOKEN, null);
    }

    public User getUser() {
        return user;
    }


    public String getAuthToken () {
        return authToken;
    }

    public Boolean hasAuthToken () {
        return authToken != null && !authToken.isEmpty();
    }

    public void setAuthToken (String authToken) {
        this.authToken = authToken;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(AUTH_PREFRENCES_TOKEN, authToken);
        editor.commit();
    }

    public void logout() {
        setAuthToken(null);
        Intent loginIntent = new Intent(context, LoginActivity.class);
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(loginIntent);
    }
}
