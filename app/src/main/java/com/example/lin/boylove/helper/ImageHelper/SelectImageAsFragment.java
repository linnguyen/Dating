package com.example.lin.boylove.helper.ImageHelper;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;

import com.example.lin.boylove.BuildConfig;
import com.example.lin.boylove.utilities.GrantPermissionUtils;

import java.io.File;
import java.io.IOException;

import me.iwf.photopicker.PhotoPicker;

/**
 * Created by lin on 04/09/2018.
 */

public class SelectImageAsFragment extends SelectImageHandler {
    Fragment fragment;

    public SelectImageAsFragment(Fragment fragment, ISelectImageListener listener) {
        super(listener);
        this.fragment = fragment;
    }

    @Override
    Activity getActivity() {
        return fragment.getActivity();
    }

    @Override
    public void pickMultipleImageFromLibrary() {
        if (GrantPermissionUtils.checkPickPhotoPermission(fragment, PICK_IMAGE_PERMISSION)) {
            PhotoPicker.builder()
                    .setPhotoCount(PhotoPicker.DEFAULT_MAX_COUNT)
                    .setShowCamera(false)
                    .setShowGif(true)
                    .setPreviewEnabled(false)
                    .start(getActivity(), fragment, PhotoPicker.REQUEST_CODE);
        }
    }

    @Override
    public void pickImageFromLibrary() {
        if (GrantPermissionUtils.checkPickPhotoPermission(fragment, PICK_IMAGE_PERMISSION)) {
            Intent intent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            fragment.startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        }
    }

    @Override
    public void dispatchTakePictureIntent() {
        if (GrantPermissionUtils.checkCameraPermission(fragment, CAMERA_PERMISSION)) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Ensure that there's a camera activity to handle the intent
            if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                // Create the File where the photo should go
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
                // Continue only if the File was successfully created
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(getActivity(),
                            BuildConfig.APPLICATION_ID + ".provider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    fragment.startActivityForResult(takePictureIntent, TAKE_IMAGE_REQUEST);
                }
            }
        }
    }
}
