package com.example.lin.boylove.helper.UploadImage;

import android.content.Context;
import android.widget.ImageView;

import com.example.lin.boylove.entity.Response.ListNewFeed;
import com.example.lin.boylove.fragment.NewFeed.NewfeedInteractor;
import com.example.lin.boylove.fragment.NewFeed.NewfeedInteractorIml;
import com.example.lin.boylove.fragment.NewFeed.NewfeedPresenter;
import com.example.lin.boylove.fragment.NewFeed.NewfeedView;

/**
 * Created by ryne on 27/10/2017.
 */

public class ImageUploadPresenterIml implements ImageUploadPresenter,
        ImageUploadPresenter.OnUploadImageFinishedListener {
    private Context context;
    private ImageUploadView view;
    private ImageUploadInteractor interactor;

    public ImageUploadPresenterIml(Context context, ImageUploadView view) {
        this.context = context;
        this.view = view;
        interactor = new ImageUploadInteractorIml(context);
    }

    @Override
    public void onSuccess(String imagePath) {
        view.hideProgressBar();
        view.onUploadImageSuccess(imagePath);
    }

    @Override
    public void onFailure(String message) {
        view.hideProgressBar();
    }

    @Override
    public void uploadImage(String imagePath) {
        interactor.uploadImage(imagePath, this);
    }
}
