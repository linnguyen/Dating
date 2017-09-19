package com.example.lin.dollar.activity.Login;

/**
 * Created by lin on 18/09/2017.
 */

public interface LoginPresenter {
    interface OnLoginFinishedListener {
        public void onUsernameError();

        public void onPasswordError();

        public void onSuccess();
    }

    public void validateCredentials(String username, String password);
}
