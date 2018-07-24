package com.example.lin.boylove.fragment.Profile;

import android.content.Context;

import com.example.lin.boylove.entity.Response.Online;

/**
 * Created by ryne on 27/10/2017.
 */

public class ProfilePresenterIml implements ProfilePresenter,
        ProfilePresenter.OnOnlineFinishedListener {
    private Context context;
    private ProfileView view;
    private ProfileInteractor interactor;

    public ProfilePresenterIml(Context context, ProfileView view) {
        this.context = context;
        this.view = view;
        interactor = new ProfileInteractorIml(context);
    }

    @Override
    public void getLstOnline() {
        view.showProgressBar();
        interactor.getListOnline(this);
    }

    @Override
    public void onSuccess(Online online) {
        view.hideProgressBar();
        view.getListOnlineSuccess(online);
    }

    @Override
    public void onFailure(String message) {
        view.hideProgressBar();
        view.showMessage(message);
    }
}
