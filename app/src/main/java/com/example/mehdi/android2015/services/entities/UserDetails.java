package com.example.mehdi.android2015.services.entities;

public class UserDetails {
    private int id;
    private boolean isContact;
    private String displayName;
    private String usernmae;
    private String avatarUrl;

    public UserDetails(int id, boolean isContact, String displayName, String usernmae, String avatarUrl) {
        this.id = id;
        this.isContact = isContact;
        this.displayName = displayName;
        this.usernmae = usernmae;
        this.avatarUrl = avatarUrl;
    }

    public int getId() {
        return id;
    }

    public boolean getContact() {
        return isContact;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getUsernmae() {
        return usernmae;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
