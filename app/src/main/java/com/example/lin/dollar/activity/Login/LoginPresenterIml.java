package com.example.lin.dollar.activity.Login;

/**
 * Created by lin on 19/09/2017.
 */

public class LoginPresenterIml implements LoginPresenter, LoginPresenter.OnLoginFinishedListener {
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterIml(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorIml();
    }

    @Override
    public void onUsernameError() {

    }

    @Override
    public void onPasswordError() {

    }

    @Override
    public void onSuccess() {
        loginView.navigateToHome();
    }

    @Override
    public void validateCredentials(String email, String password) {
        loginView.showProgress();
        loginInteractor.login(email, password, this);
    }
}
