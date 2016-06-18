package com.example.mehdi.android2015.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.mehdi.android2015.R;
import com.example.mehdi.android2015.activities.BaseActivity;
import com.example.mehdi.android2015.services.Contact;
import com.example.mehdi.android2015.views.ContactsRequestAdapter;
import com.squareup.otto.Subscribe;

public class PendingContactRequestFragment extends BaseFragment {

    public FrameLayout progressFrame;
    public ContactsRequestAdapter adapter;
    public ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pending_contact_requests, container, false);
        progressFrame = (FrameLayout) view.findViewById(R.id.fragment_pending_contact_request_progressFrame);
        listView = (ListView) view.findViewById(R.id.fragment_pending_contact_request_list);
        adapter = new ContactsRequestAdapter((BaseActivity) getActivity());
        listView.setAdapter(adapter);

        bus.post(new Contact.GetContactRequestsRequest(true));
        return view;
    }

    @Subscribe
    public void onGetContactRequests(Contact.GetContactRequestsResponse response) {

        progressFrame.setVisibility(View.GONE);
        adapter.clear();
        adapter.addAll(response.requests);
    }
}