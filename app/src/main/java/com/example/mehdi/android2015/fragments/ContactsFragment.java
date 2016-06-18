package com.example.mehdi.android2015.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mehdi.android2015.R;
import com.example.mehdi.android2015.activities.AddContactActivity;
import com.example.mehdi.android2015.activities.BaseActivity;
import com.example.mehdi.android2015.services.Contact;
import com.example.mehdi.android2015.views.UserDetailsAdapter;
import com.squareup.otto.Subscribe;

public class ContactsFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private UserDetailsAdapter adapter;
    private View progressFrame;
    private View view;
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.fragment_contacts, container, false);

        adapter = new UserDetailsAdapter((BaseActivity) getActivity());
        progressFrame = view.findViewById(R.id.fragment_contacts_progress_frame);

        listView = (ListView) view.findViewById(R.id.fragment_contact_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        bus.post(new Contact.GetContactsRequest(false));

        return view;
    }

    @Subscribe
    public void onContactsResponse(Contact.GetContactsResponse response) {
        progressFrame.setVisibility(View.GONE);
        adapter.clear();
        if(response.contacts.size() > 0) {
            adapter.addAll(response.contacts);
        } else {
            listView.setEmptyView(view.findViewById(R.id.fragment_contacts_no_contact));
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_contacts, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.fragment_contacts_menu_addContact) {
            startActivity(new Intent(getActivity(), AddContactActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
