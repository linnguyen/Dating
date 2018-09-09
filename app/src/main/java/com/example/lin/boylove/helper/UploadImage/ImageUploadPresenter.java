package com.example.lin.boylove.helper.UploadImage;

/**
 * Created by ryne on 27/10/2017.
 */

public interface ImageUploadPresenter {

    interface OnUploadImageFinishedListener {
        void onSuccess(String iamgePath);

        void onFailure(String message);
    }

    void uploadImage(String imagePath);
}
