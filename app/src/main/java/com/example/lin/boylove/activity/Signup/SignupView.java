package com.example.lin.boylove.activity.Signup;

/**
 * Created by lin on 18/09/2017.
 */

public interface SignupView {
    void showProgress();

    void showMessage(String message);

    void hideProgress();

    void navigateToHome();
}
