package com.example.lin.dollar.activity.Login;

import android.content.Context;

/**
 * Created by lin on 19/09/2017.
 */

public class LoginPresenterIml implements LoginPresenter, LoginPresenter.OnLoginFinishedListener {
    private LoginView loginView;
    private LoginInteractor loginInteractor;
    private Context context;

    public LoginPresenterIml(LoginView loginView, Context context) {
        this.context = context;
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorIml(context);
    }

    @Override
    public void onUsernameError() {

    }

    @Override
    public void onPasswordError() {

    }

    @Override
    public void onSuccess(String message) {
        loginView.hideProgress();
        loginView.navigateToHome();
        loginView.showMessage(message);
    }

    @Override
    public void onFailure(String message) {
        loginView.hideProgress();
        loginView.showMessage(message);
    }

    @Override
    public void validateCredentials(String email, String password) {
        loginView.showProgress();
        loginInteractor.login(email, password, this);
    }
}
