package com.example.lin.boylove.activity.Settings;

import android.content.Context;

/**
 * Created by lin on 19/09/2017.
 */

public class SettingPresenterIml implements SettingPresenter {
    private SettingView view;
    private SettingInteractor interactor;
    private Context context;

    public SettingPresenterIml(SettingView loginView, Context context) {
        this.context = context;
        this.view = loginView;
        this.interactor = new SettingInteractorIml(context);
    }

    @Override
    public void logout() {
        interactor.logout();
    }
}
