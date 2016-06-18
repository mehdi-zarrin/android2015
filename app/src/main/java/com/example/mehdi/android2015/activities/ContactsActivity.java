package com.example.mehdi.android2015.activities;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.mehdi.android2015.R;
import com.example.mehdi.android2015.fragments.ContactsFragment;
import com.example.mehdi.android2015.fragments.PendingContactRequestFragment;
import com.example.mehdi.android2015.views.MainNavDrawer;


public class ContactsActivity extends BaseAuthenticatedActivity implements AdapterView.OnItemSelectedListener {

    private ObjectAnimator currentAnimation;
    private ArrayAdapter<ContactsSpinnerItem> adapter;
    @Override
    protected void onAppCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_contacts);
        setNavDrawer(new MainNavDrawer(this));

        adapter = new ArrayAdapter<ContactsSpinnerItem>(this, R.layout.list_item_toolbar_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        adapter.add(new ContactsSpinnerItem("Contacts", Color.parseColor("#00BCD4"), ContactsFragment.class));
        adapter.add(new ContactsSpinnerItem(
                "Pending Contacts Requests",
                Color.parseColor("#607D8B"),
                PendingContactRequestFragment.class));

        Spinner spinner = (Spinner) findViewById(R.id.activity_contact_spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        getSupportActionBar().setTitle(null);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ContactsSpinnerItem item = adapter.getItem(position);
        if(item == null)
            return;

        if(currentAnimation != null)
            currentAnimation.end();

        int currentColor = ( (ColorDrawable) toolbar.getBackground() ).getColor();
        currentAnimation = ObjectAnimator.ofObject(toolbar, "backgroundColor", new ArgbEvaluator(), currentColor, item.getColor())
                .setDuration(250);
        currentAnimation.start();

        Fragment fragment;
        try {
            fragment = (Fragment) item.getFragment().newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_contacts_fragment_container, fragment)
                    .commit();
        } catch(Exception e) {
            Log.e("ContactsActivity" , "could not create instance of the fragment! " +  item.getFragment().getName(), e);
            return;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class ContactsSpinnerItem {
        private final String title;
        private final int color;
        private Class fragment;

        public ContactsSpinnerItem(String title, int color, Class fragment) {
            this.title = title;
            this.color = color;
            this.fragment = fragment;
        }


        public String getTitle() {
            return title;
        }

        public int getColor() {
            return color;
        }

        public Class getFragment() {
            return fragment;
        }

        @Override
        public String toString() {
            return getTitle();
        }
    }
}
