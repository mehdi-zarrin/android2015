package com.example.mehdi.android2015.dialogs;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import com.example.mehdi.android2015.core.ApplicationBase;
import com.squareup.otto.Bus;

public abstract class BaseDialogFragment extends DialogFragment {
    protected ApplicationBase application;
    protected Bus bus;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (ApplicationBase) getActivity().getApplication();
        bus = application.getBus();
        bus.register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }
}
