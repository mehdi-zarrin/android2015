package com.example.mehdi.android2015.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mehdi.android2015.R;
import com.example.mehdi.android2015.core.User;
import com.example.mehdi.android2015.dialogs.ChangePasswordDialog;
import com.example.mehdi.android2015.services.Account;
import com.example.mehdi.android2015.views.MainNavDrawer;
import com.soundcloud.android.crop.Crop;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends BaseAuthenticatedActivity implements View.OnClickListener {

    private static final int STATE_VIEWING = 1;
    private static final int STATE_EDITING = 2;
    private static final int REQUEST_SELECT_IMAGE = 100;
    private static final String BUNDLE_STATE = "BUNDLE_STATE";
    private static boolean isProgressBarVisible;

    private int currentState;
    private EditText displayNameText;
    private EditText emailText;
    private View changeAvatarButton;
    private ActionMode editProfileActionMode;
    private ImageView avatarView;
    private FrameLayout avatarProgressFrame;
    private File tmpOutputFile;
    private Dialog progressDialog;


    @Override
    protected void onAppCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_profile);
        setNavDrawer(new MainNavDrawer(this));
        avatarView = (ImageView) findViewById(R.id.activity_profile_avatar);
        avatarProgressFrame = (FrameLayout) findViewById(R.id.activity_profile_avatarProgressFrame);
        tmpOutputFile = new File(getExternalCacheDir(), "temp-image.jpg");

        avatarProgressFrame.setVisibility(View.GONE);
        changeAvatarButton = findViewById(R.id.activity_profile_changeAvatar);
        displayNameText = (EditText) findViewById(R.id.activity_profile_displayName);
        emailText = (EditText) findViewById(R.id.activity_profile_email);

        avatarView.setOnClickListener(this);
        changeAvatarButton.setOnClickListener(this);

        User user = application.getAuth().getUser();
        getSupportActionBar().setTitle(user.getDisplayName());
        if(savedInstanceState == null) {
            displayNameText.setText(user.getDisplayName());
            emailText.setText(user.getEmail());
            changeState(STATE_VIEWING);
        } else
            changeState(savedInstanceState.getInt(BUNDLE_STATE));


        if(isProgressBarVisible)
            setProgressBarVisible(true);


    }

    @Subscribe
    public void onAvatarUpdated(Account.changeAvatarResponse response) {
        Log.e("DDDD" , "update avatar response called!");
        avatarProgressFrame.setVisibility(View.GONE);
        if(!response.didSucceed()) {
            response.showErrorToast(this);
        }
    }

    @Subscribe
    public void onProfileUpdated(Account.updateProfileResponse response) {
        Log.e("DDDD", "update profile response called!");
        if(!response.didSucceed()) {
            response.showErrorToast(this);
            changeState(STATE_EDITING);
        }

        displayNameText.setError(response.getPropertyError("displayName"));
        emailText.setError(response.getPropertyError("email"));
        setProgressBarVisible(false);
    }

    @Subscribe
    public void onUserDetailsUpdated(Account.UserDetailsUpdatedEvent event) {
        getSupportActionBar().setTitle(event.user.getDisplayName());
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_STATE, currentState);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if(viewId == R.id.activity_profile_changeAvatar || viewId == R.id.activity_profile_avatar)
            changeAvatar();
    }

    private void changeAvatar() {
//        List<Intent> otherImageCaptureIntent = new ArrayList<>();
//        List<ResolveInfo> otherImageCaptureActivities = getPackageManager().queryIntentActivities(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), 0);
//        for(ResolveInfo info : otherImageCaptureActivities) {
//            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            captureIntent.setClassName(info.activityInfo.packageName, info.activityInfo.name);
//            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tmpOutputFile));
//            otherImageCaptureIntent.add(captureIntent);
//        }
//
//        Intent selectImageIntent = new Intent(Intent.ACTION_PICK);
//        selectImageIntent.setType("image/*");
//
//        Intent chooser = Intent.createChooser(selectImageIntent, "Choose Avatar");
//        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, otherImageCaptureIntent.toArray(new Parcelable[otherImageCaptureActivities.size()]));
//        startActivityForResult(chooser, REQUEST_SELECT_IMAGE);
        Crop.pickImage(this);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {

        if(resultCode != RESULT_OK) {
            return;
        }

        if(requestCode == Crop.REQUEST_PICK) {

            beginCrop(result.getData());

        } else if(requestCode == Crop.REQUEST_CROP) {

            handleCrop(resultCode, result);
        }
    }


    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(this);
    }

    private void handleCrop(int resultCode, Intent result) {

        if (resultCode == RESULT_OK) {
            // reset palceholder from previous choosed image
            // publish an event
            avatarProgressFrame.setVisibility(View.VISIBLE);
            bus.post(new Account.changeAvatarRequest("somefilename"));
            avatarView.setImageResource(0);
            avatarView.setImageURI(Crop.getOutput(result));
        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_profile_menu_edit) {
            changeState(STATE_EDITING);
            return true;
        } else if(itemId == R.id.activity_profile_changePassword) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().addToBackStack(null);
            ChangePasswordDialog dialog = new ChangePasswordDialog();
            dialog.show(transaction, null);
            return true;
        }

        return false;
    }

    private void changeState(int state) {
        if(state == currentState)
            return;

        currentState = state;
        if(state == STATE_VIEWING) {
            displayNameText.setEnabled(false);
            emailText.setEnabled(false);
            changeAvatarButton.setVisibility(View.VISIBLE);

            if(editProfileActionMode != null) {
                editProfileActionMode.finish();
                editProfileActionMode = null;
            }


        } else if (state == STATE_EDITING) {

            displayNameText.setEnabled(true);
            emailText.setEnabled(true);
            changeAvatarButton.setVisibility(View.GONE);

            editProfileActionMode = toolbar.startActionMode(new EditProfileActionCallback());

        } else {
            throw new IllegalArgumentException("Invalid state " + state ) ;
        }
    }

    private class EditProfileActionCallback implements ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getMenuInflater().inflate(R.menu.activity_profile_edit, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int itemId = item.getItemId();
            if(itemId == R.id.activity_profile_edit_menudone) {
                // TODO : send request to update displayName and Email to the server
                setProgressBarVisible(true);
                changeState(STATE_VIEWING);
                bus.post(new Account.updateProfileRequest(
                        displayNameText.getText().toString(),
                        emailText.getText().toString()));
                return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if(currentState != STATE_VIEWING) {
                changeState(STATE_VIEWING);
            }
        }
    }

    private void setProgressBarVisible(boolean visible) {
        if(visible) {
            progressDialog = new ProgressDialog.Builder(this)
                    .setTitle("Updating Profile")
                    .setCancelable(false)
                    .show();
        } else if(progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }

        isProgressBarVisible = visible;
    }
}
