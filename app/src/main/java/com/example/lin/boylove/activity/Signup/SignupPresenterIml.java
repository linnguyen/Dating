package com.example.lin.boylove.activity.Signup;

import android.content.Context;

/**
 * Created by lin on 19/09/2017.
 */

public class SignupPresenterIml implements
        SignupPresenter, SignupPresenter.OnSignupFinishedListener {
    private SignupView view;
    private SignupInteractor interactor;
    private Context context;

    public SignupPresenterIml(SignupView view, Context context) {
        this.context = context;
        this.view = view;
        this.interactor = new SignupInteractorIml(context);
    }

    @Override
    public void onSuccess(String message) {
        view.hideProgress();
        view.navigateToHome();
    }

    @Override
    public void onFailure(String message) {
        view.hideProgress();
        view.showMessage(message);
    }

    @Override
    public void registerAccount(String dolaxName, String email, String password, String confirmPassword, String imageUrl) {
        view.showProgress();
        interactor.createAccount(dolaxName, email, password, confirmPassword, imageUrl, this);
    }
}
