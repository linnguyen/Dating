package com.example.lin.boylove.activity.Signup;

/**
 * Created by lin on 19/09/2017.
 */

public interface SignupInteractor {
    void createAccount(String dolaxName, String email, String password, String confirmPassword, String imageUrl, SignupPresenter.OnSignupFinishedListener listener);
}
