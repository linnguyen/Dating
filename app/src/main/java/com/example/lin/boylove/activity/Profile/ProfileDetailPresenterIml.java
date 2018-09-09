package com.example.lin.boylove.activity.Profile;

import android.content.Context;

/**
 * Created by lin on 19/09/2017.
 */

public class ProfileDetailPresenterIml implements ProfileDetailPresenter, ProfileDetailPresenter.OnChatFinishedListener {
    private ProfileDetailView view;
    private ProfileDetailInteractor interactor;
    private Context context;

    public ProfileDetailPresenterIml(ProfileDetailView view, Context context) {
        this.context = context;
        this.view = view;
        this.interactor = new ProfileDetailInteractorIml(context);
    }
}
