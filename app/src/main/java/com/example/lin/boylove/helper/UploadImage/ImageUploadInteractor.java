package com.example.lin.boylove.helper.UploadImage;

/**
 * Created by ryne on 27/10/2017.
 */

public interface ImageUploadInteractor {
    void uploadImage(String imagePath, ImageUploadPresenter.OnUploadImageFinishedListener listener);
}
