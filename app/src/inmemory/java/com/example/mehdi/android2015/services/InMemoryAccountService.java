package com.example.mehdi.android2015.services;

import android.util.Log;

import com.example.mehdi.android2015.core.ApplicationBase;
import com.example.mehdi.android2015.core.Auth;
import com.example.mehdi.android2015.core.User;
import com.squareup.otto.Subscribe;

public class InMemoryAccountService extends BaseInMemoryService {
    public InMemoryAccountService(ApplicationBase application) {
        super(application);
    }

    @Subscribe
    public void onUpdateRequestRequest(final Account.updateProfileRequest request) {
        final Account.updateProfileResponse response = new Account.updateProfileResponse();
        if(request.displayName.equals("mehdi")) {
            response.setPropertyError("displayName", "username already has been taken!");
        }
//        postWithDelay(response, 4000);
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                User user = application.getAuth().getUser();
                user.setDisplayName(request.displayName);
                user.setEmail(request.email);
                bus.post(response);
                bus.post(new Account.UserDetailsUpdatedEvent(user));
            }
        }, 3000, 5000);
    }

    @Subscribe
    public void onChangeAvatarRequest(final Account.changeAvatarRequest request) {
        Account.changeAvatarResponse response = new Account.changeAvatarResponse();
//        postWithDelay(response, 2000);
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                User user = application.getAuth().getUser();
                user.setAvatarUrl(request.filename);

                bus.post(new Account.changeAvatarResponse());
                bus.post(new Account.UserDetailsUpdatedEvent(user));
            }
        }, 4000, 5000);
    }

    @Subscribe
    public void onChangePasswordRequest(Account.changePasswordRequest request) {
        Account.changePasswordResponse response = new Account.changePasswordResponse();
        if(!request.newPassword.equals(request.confirmNewPassword)) {
            response.setPropertyError("confirmNewPassword", "Confirm is not match with new password!");
        }

        if(request.newPassword.length() < 4) {
            response.setPropertyError("newPassword", "password should be at least 4 chars!");
        }

        postWithDelay(response, 2000);
    }


    @Subscribe
    public void loginWithUsername(final Account.LoginWithUsernameRequest request) {
        final Account.LoginWithUsernameResponse response = new Account.LoginWithUsernameResponse();
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("DDDD" , "request username is : " + request.Username);
                if(request.Username.equals("mehdi")) {
                    response.setPropertyError("username", "username is wrong");
                }
                loginUser(response);
                bus.post(response);
            }
        }, 1000, 2000);

    }

    @Subscribe
    public void loginWithExternalToken(Account.LoginWithExternalTokenRequest request) {
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                Account.LoginWithExternalTokenResponse response = new Account.LoginWithExternalTokenResponse();
                loginUser(response);
                bus.post(response);
            }
        }, 1000, 2000);

    }

    @Subscribe
    public void loginWithLocalToken(Account.LoginWithLocalTokenRequest request) {
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                Account.LoginWithLocalTokenResponse response = new Account.LoginWithLocalTokenResponse();
                loginUser(response);
                bus.post(response);
            }
        }, 1000, 2000);
    }

    @Subscribe
    public void register(Account.RegisterRequest request) {
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                Account.RegisterResponse response = new Account.RegisterResponse();
                loginUser(response);
                bus.post(response);
            }
        }, 1000, 2000);
    }

    @Subscribe
    public void externalRegister(Account.RegisterWithExternalTokenRequest request) {
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                Account.RegisterWithExternalTokenResponse response = new Account.RegisterWithExternalTokenResponse();
                loginUser(response);
                bus.post(response);
            }
        }, 1000, 2000);
    }

    private void loginUser(Account.UserResponse response) {
        Auth auth = application.getAuth();
        User user = auth.getUser();

        user.setDisplayName("Mehdi Zarrin");
        user.setEmail("mehdi.zarrin@gmail.com");
        user.setUsername("mehdi.zarrin");
        user.setId(123);
        user.setAvatarUrl("http://gravatar.com/avatar/1?id=identicon");
        user.setLoggedIn(true);

        bus.post(new Account.UserDetailsUpdatedEvent(user));
        auth.setAuthToken("fakeauthtoken");

        response.DisplayName = user.getDisplayName();
        response.Email = user.getEmail();
        response.AvatarUrl = user.getAvatarUrl();
        response.id = user.getId();
        response.UserName = user.getUsername();
        response.AuthToken = auth.getAuthToken();

    }
}
