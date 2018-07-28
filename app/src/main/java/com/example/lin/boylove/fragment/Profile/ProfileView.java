package com.example.lin.boylove.fragment.Profile;

import com.example.lin.boylove.entity.Response.User;

/**
 * Created by ryne on 27/10/2017.
 */

public interface ProfileView {
    void showProgressBar();

    void hideProgressBar();

    void showMessage(String message);

    void onGetUserProfileSuccess(User user);
}
