package com.example.mehdi.android2015.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mehdi.android2015.R;
import com.example.mehdi.android2015.events.LoginEvent;


public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private Button loginButton;
//    private Callbacks callbacks;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        loginButton = (Button) view.findViewById(R.id.fragment_login_loginButton);
        loginButton.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        if(view ==  loginButton) {
            application.getAuth().getUser().setLoggedIn(true);
            bus.post(new LoginEvent(true));
//            callbacks.onLoggedIn();
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        callbacks = (Callbacks) context;
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        callbacks = null;
//    }

//    public interface Callbacks {
//        void onLoggedIn();
//    }
}
