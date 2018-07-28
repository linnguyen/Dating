package com.example.lin.boylove.fragment.Profile;

import android.content.Context;

import com.example.lin.boylove.entity.Response.User;

/**
 * Created by ryne on 27/10/2017.
 */

public class ProfilePresenterIml implements ProfilePresenter,
        ProfilePresenter.OnProfileFinishedListener {
    private Context context;
    private ProfileView view;
    private ProfileInteractor interactor;

    public ProfilePresenterIml(Context context, ProfileView view) {
        this.context = context;
        this.view = view;
        interactor = new ProfileInteractorIml(context);
    }

    @Override
    public void onSuccess(User user) {
        view.showProgressBar();
        view.onGetUserProfileSuccess(user);
    }

    @Override
    public void onFailure(String message) {
        view.hideProgressBar();
        view.showMessage(message);
    }

    @Override
    public void getUserProfile() {
        view.showProgressBar();
        interactor.getUserProfile(this);
    }
}
