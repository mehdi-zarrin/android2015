package com.example.mehdi.android2015.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mehdi.android2015.R;
import com.example.mehdi.android2015.events.LoginEvent;
import com.example.mehdi.android2015.services.Account;
import com.squareup.otto.Subscribe;


public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private Button loginButton;
    private TextView usernameText, passwordText;
    private View progressBar;
    private String defaultLoginButtonText;
//    private Callbacks callbacks;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        loginButton = (Button) view.findViewById(R.id.fragment_login_loginButton);
        loginButton.setOnClickListener(this);

        usernameText = (TextView) view.findViewById(R.id.fragment_login_userName);
        passwordText = (TextView) view.findViewById(R.id.fragment_login_password);

        progressBar = (ProgressBar) view.findViewById(R.id.fragment_login_progressbar);
        defaultLoginButtonText = loginButton.getText().toString();
        return view;
    }


    @Override
    public void onClick(View view) {
        if(view ==  loginButton) {
            progressBar.setVisibility(View.VISIBLE);
            loginButton.setText("");
            usernameText.setEnabled(true);
            passwordText.setEnabled(true);
            loginButton.setEnabled(false);

            bus.post(new Account.LoginWithUsernameRequest(
                    usernameText.getText().toString(),
                    passwordText.getText().toString()));
        }
    }


    @Subscribe
    public void onLoginWithUsername(Account.LoginWithUsernameResponse response) {
        response.showErrorToast(getActivity());

        if(response.didSucceed()) {
            bus.post(new LoginEvent(true));
            return;
        }
        loginButton.setEnabled(true);
        usernameText.setError(response.getPropertyError("username"));
        passwordText.setError(response.getPropertyError("password"));
        loginButton.setText(defaultLoginButtonText);
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        callbacks = (Callbacks) context;
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        callbacks = null;
//    }
//
//    public interface Callbacks {
//        void onLoggedIn();
//    }
}
