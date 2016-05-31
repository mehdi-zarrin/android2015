package com.example.mehdi.android2015.events;

public class LoginEvent {
    
    private Boolean loggedIn;

    public LoginEvent(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }


    public Boolean getLoggedIn() {
        return loggedIn;
    }
}
