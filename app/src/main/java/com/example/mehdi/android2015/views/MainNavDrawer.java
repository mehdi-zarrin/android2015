package com.example.mehdi.android2015.views;

import android.view.View;
import android.widget.Toast;

import com.example.mehdi.android2015.R;
import com.example.mehdi.android2015.activities.BaseActivity;
import com.example.mehdi.android2015.activities.MainActivity;
import com.example.mehdi.android2015.activities.ProfileActivity;

public class MainNavDrawer extends NavDrawer {
    public MainNavDrawer(final BaseActivity activity) {
        super(activity);
        addItem(new NavDrawer.ActivityDrawerItem(MainActivity.class, "Inbox", "30", R.drawable.ic_action_unread, R.id.include_main_nav_drawer_topItems));
        addItem(new NavDrawer.ActivityDrawerItem(ProfileActivity.class, "Profile", "30", R.drawable.ic_action_done, R.id.include_main_nav_drawer_topItems));
        addItem(new NavDrawer.BasicNavDrawerItem("Logout", null, R.drawable.ic_action_backspace,R.id.include_main_nav_drawer_bottomItems) {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Your are now logged out", Toast.LENGTH_LONG).show();
            }
        });
    }
}
