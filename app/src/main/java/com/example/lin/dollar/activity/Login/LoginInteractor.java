package com.example.lin.dollar.activity.Login;

/**
 * Created by lin on 19/09/2017.
 */

public interface LoginInteractor {
    public void login(String username, String password, LoginPresenter.OnLoginFinishedListener listener);
}
