package com.example.lin.boylove.activity.Login;

/**
 * Created by lin on 18/09/2017.
 */

public interface LoginPresenter {
    interface OnLoginFinishedListener {
        void onSuccess(String message);

        void onFailure(String message);
    }

    void validateCredentials(String email, String password);
}
