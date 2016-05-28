package com.example.mehdi.android2015.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.mehdi.android2015.core.ApplicationBase;

public abstract class BaseFragment extends Fragment {
    protected ApplicationBase application;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (ApplicationBase) getActivity().getApplication();
    }
}
