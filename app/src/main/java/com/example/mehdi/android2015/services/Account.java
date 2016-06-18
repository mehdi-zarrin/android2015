package com.example.mehdi.android2015.services;

import com.example.mehdi.android2015.core.ServiceResponse;
import com.example.mehdi.android2015.core.User;

public class Account {

    private Account() {
    }

    public static class UserResponse extends ServiceResponse {
        public int id;
        public String DisplayName;
        public String UserName;
        public String Email;
        public String AuthToken;
        public String HasPassword;
        public String AvatarUrl;
    }

    public static class LoginWithUsernameRequest {
        public String Username;
        public String Password;

        public LoginWithUsernameRequest(String username, String password) {
            Username = username;
            Password = password;
        }
    }
    public static class LoginWithUsernameResponse extends UserResponse {
    }


    public static class LoginWithLocalTokenRequest {
        public String AuthToken;

        public LoginWithLocalTokenRequest(String authToken) {
            AuthToken = authToken;
        }
    }
    public static class LoginWithLocalTokenResponse extends UserResponse {}


    public static class LoginWithExternalTokenRequest {
        public String Provider;
        public String Token;
        public String ClientId;

        public LoginWithExternalTokenRequest(String provider, String token) {
            Provider = provider;
            Token = token;
            ClientId = "android";
        }
    }
    public static class LoginWithExternalTokenResponse extends UserResponse {}


    public static class RegisterRequest {
        public String UserName;
        public String Email;
        public String Password;
        public String ClientId;

        public RegisterRequest(String userName, String email, String password) {
            UserName = userName;
            Email = email;
            Password = password;
            ClientId = "android";
        }
    }
    public static class RegisterResponse extends UserResponse {}


    public static class RegisterWithExternalTokenRequest {
        public String Username, Email, Provider, Token, ClientId;

        public RegisterWithExternalTokenRequest(String username, String email, String provider, String token) {
            Username = username;
            Email = email;
            Provider = provider;
            Token = token;
            ClientId = "android";
        }
    }
    public static class RegisterWithExternalTokenResponse extends UserResponse {}


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

    public static class UserDetailsUpdatedEvent {
        public User user;
        public UserDetailsUpdatedEvent(User user) {
            this.user = user;
        }
    }
}
