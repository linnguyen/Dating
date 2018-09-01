package com.example.lin.boylove.activity.Signup;

/**
 * Created by lin on 18/09/2017.
 */

public interface SignupPresenter {
    interface OnSignupFinishedListener {
        void onSuccess(String message);

        void onFailure(String message);
    }

    void registerAccount(String dolaxName, String email, String password, String confirmPassword, String imageUrl);
}
