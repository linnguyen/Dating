package com.example.lin.boylove.helper.UploadImage;

/**
 * Created by lin on 04/09/2018.
 */

public interface ImageUploadView {
    void showProgressBar();

    void hideProgressBar();

    void onUploadImageSuccess(String imagePath);
}
