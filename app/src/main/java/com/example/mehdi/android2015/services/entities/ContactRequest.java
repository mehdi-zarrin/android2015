package com.example.mehdi.android2015.services.entities;

import java.util.Calendar;

public class ContactRequest {
    private int id;
    private boolean isFromUs;
    private UserDetails user;
    private Calendar createAt;

    public ContactRequest(int id, boolean isFromUs, UserDetails user, Calendar createAt) {
        this.id = id;
        this.isFromUs = isFromUs;
        this.user = user;
        this.createAt = createAt;
    }

    public int getId() {
        return id;
    }

    public boolean getFromUs() {
        return isFromUs;
    }

    public UserDetails getUser() {
        return user;
    }

    public Calendar getCreateAt() {
        return createAt;
    }
}
