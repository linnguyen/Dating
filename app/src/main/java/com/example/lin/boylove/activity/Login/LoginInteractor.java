package com.example.lin.boylove.activity.Login;

/**
 * Created by lin on 19/09/2017.
 */

public interface LoginInteractor {
    void login(String username, String password, LoginPresenter.OnLoginFinishedListener listener);
}
