package com.example.mehdi.android2015.activities;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mehdi.android2015.R;
import com.example.mehdi.android2015.views.MainNavDrawer;
import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends BaseAuthenticatedActivity implements View.OnClickListener {
    private static final int REQUEST_SELECT_IMAGE = 100;
    private ImageView avatarView;
    private FrameLayout avatarProgressFrame;
    private File tmpOutputFile;
    @Override
    protected void onAppCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_profile);
        setNavDrawer(new MainNavDrawer(this));

        avatarView = (ImageView) findViewById(R.id.activity_profile_avatar);
        avatarProgressFrame = (FrameLayout) findViewById(R.id.activity_profile_avatarProgressFrame);
        tmpOutputFile = new File(getExternalCacheDir(), "temp-image.jpg");

        avatarProgressFrame.setVisibility(View.GONE);
        avatarView.setOnClickListener(this);
        findViewById(R.id.activity_profile_changeAvatar).setOnClickListener(this);

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
            avatarView.setImageResource(0);
            avatarView.setImageURI(Crop.getOutput(result));
        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
