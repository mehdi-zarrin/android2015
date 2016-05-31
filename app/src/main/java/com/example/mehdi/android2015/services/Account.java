package com.example.mehdi.android2015.services;

import com.example.mehdi.android2015.core.ServiceResponse;

public class Account {
    private Account() {
    }

    public static class changeAvatarRequest {
        public String filename;
        public changeAvatarRequest(String filename) {
            this.filename = filename;
        }

    }


    public static class changeAvatarResponse extends ServiceResponse {
    }


    public static class updateProfileRequest {
        public String displayName;
        public String email;

        public updateProfileRequest(String displayName, String email) {
            this.displayName = displayName;
            this.email = email;
        }
    }

    public static class updateProfileResponse extends ServiceResponse {


    }


    public static class changePasswordRequest {
        public String currentPassword;
        public String newPassword;
        public String confirmNewPassword;

        public changePasswordRequest(String currentPassword, String newPassword, String confirmNewPassword) {
            this.currentPassword = currentPassword;
            this.newPassword = newPassword;
            this.confirmNewPassword = confirmNewPassword;
        }
    }


    public static class changePasswordResponse extends ServiceResponse{

    }
}
