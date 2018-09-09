package com.example.lin.boylove.helper.ImageHelper;

import android.content.Intent;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by lin on 04/09/2018.
 */

public interface ISelectImageView {
    int CAMERA_PERMISSION = 3;
    int PICK_IMAGE_PERMISSION = 4;

    void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void pickMultipleImageFromLibrary();

    void pickImageFromLibrary();

    void dispatchTakePictureIntent();

    interface ISelectImageListener {
        void onImagePathReturn(List<String> paths);

        void onSelectImageError();
    }
}
