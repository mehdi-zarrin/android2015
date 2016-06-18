package com.example.mehdi.android2015.views;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mehdi.android2015.R;
import com.example.mehdi.android2015.activities.BaseActivity;
import com.example.mehdi.android2015.activities.ContactsActivity;
import com.example.mehdi.android2015.activities.MainActivity;
import com.example.mehdi.android2015.activities.ProfileActivity;
import com.example.mehdi.android2015.activities.SentMessages;
import com.example.mehdi.android2015.core.User;
import com.example.mehdi.android2015.services.Account;
import com.squareup.otto.Subscribe;

public class MainNavDrawer extends NavDrawer {
    private TextView displayNameText;
    private ImageView avatarImage;

    public MainNavDrawer(final BaseActivity activity) {
        super(activity);
        addItem(new NavDrawer.ActivityDrawerItem(MainActivity.class, "Inbox", "30", R.drawable.ic_action_unread, R.id.include_main_nav_drawer_topItems));
        addItem(new NavDrawer.ActivityDrawerItem(ProfileActivity.class, "Profile", "30", R.drawable.ic_action_user, R.id.include_main_nav_drawer_topItems));
        addItem(new NavDrawer.ActivityDrawerItem(SentMessages.class, "Sent Messages", "30", R.drawable.ic_action_send_now, R.id.include_main_nav_drawer_topItems));
        addItem(new NavDrawer.ActivityDrawerItem(ContactsActivity.class, "Contacts", "30", R.drawable.ic_action_person, R.id.include_main_nav_drawer_topItems));
        addItem(new NavDrawer.BasicNavDrawerItem("Logout", null, R.drawable.ic_action_backspace,R.id.include_main_nav_drawer_bottomItems) {
            @Override
            public void onClick(View v) {
                activity.application.getAuth().logout();
            }
        });


        displayNameText = (TextView) navDrawerView.findViewById(R.id.include_main_nav_drawer_displayName);
        avatarImage = (ImageView) navDrawerView.findViewById(R.id.include_main_nav_drawer_avatar);

        User loggedInUser = activity.application.getAuth().getUser();
        Log.e("DDDD" , "current username is :" + loggedInUser.getDisplayName());
        displayNameText.setText(loggedInUser.getDisplayName());
    }



    @Subscribe
    public void onUserDatailsUpdated(Account.UserDetailsUpdatedEvent event) {
        // todo update avatar url
        displayNameText.setText(event.user.getDisplayName());
    }
}
