package com.example.lin.dollar.activity.Login;

/**
 * Created by lin on 18/09/2017.
 */

public interface LoginPresenter {
    interface OnLoginFinishedListener {
        void onUsernameError();

        void onPasswordError();

        void onSuccess();
    }

    void validateCredentials(String email, String password);
}
