package com.example.lin.boylove.activity.Home;

import android.content.Context;

import com.example.lin.boylove.activity.Login.LoginInteractor;
import com.example.lin.boylove.activity.Login.LoginInteractorIml;
import com.example.lin.boylove.activity.Login.LoginView;

/**
 * Created by lin on 19/09/2017.
 */

public class HomePresenterIml implements HomePresenter,
        HomePresenter.OnHomeFinishedListener {
    private HomeView view;
    private HomeInteractor interactor;
    private Context context;

    public HomePresenterIml(HomeView view, Context context) {
        this.context = context;
        this.view = view;
        this.interactor = new HomeInteractorIml(context);
    }

    @Override
    public void onSuccess(String message) {
        view.hideProgress();
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void logout() {
        interactor.logout(this);
    }
}
