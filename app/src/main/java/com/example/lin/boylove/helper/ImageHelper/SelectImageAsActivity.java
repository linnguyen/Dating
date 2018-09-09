package com.example.lin.boylove.helper.ImageHelper;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.example.lin.boylove.BuildConfig;
import com.example.lin.boylove.utilities.GrantPermissionUtils;

import java.io.File;
import java.io.IOException;

import me.iwf.photopicker.PhotoPicker;

/**
 * Created by lin on 04/09/2018.
 */

public class SelectImageAsActivity extends SelectImageHandler {
    Activity activity;

    public SelectImageAsActivity(Activity activity, ISelectImageView.ISelectImageListener listener) {
        super(listener);
        this.activity = activity;
    }

    @Override
    Activity getActivity() {
        return activity;
    }

    @Override
    public void pickMultipleImageFromLibrary() {
        if (GrantPermissionUtils.checkPickPhotoPermission(activity, PICK_IMAGE_PERMISSION)) {
            PhotoPicker.builder()
                    .setPhotoCount(PhotoPicker.DEFAULT_MAX_COUNT)
                    .setShowCamera(false)
                    .setShowGif(true)
                    .setPreviewEnabled(false)
                    .start(activity, PhotoPicker.REQUEST_CODE);
        }
    }

    @Override
    public void pickImageFromLibrary() {
        if (GrantPermissionUtils.checkPickPhotoPermission(activity, PICK_IMAGE_PERMISSION)) {
            Intent intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        }
    }

    @Override
    public void dispatchTakePictureIntent() {
        if (GrantPermissionUtils.checkCameraPermission(activity, CAMERA_PERMISSION)) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Ensure that there's a camera activity to handle the intent
            if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
                // Create the File where the photo should go
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
                // Continue only if the File was successfully created
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(activity,
                            BuildConfig.APPLICATION_ID + ".provider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    activity.startActivityForResult(takePictureIntent, TAKE_IMAGE_REQUEST);
                }
            }
        }
    }
}
