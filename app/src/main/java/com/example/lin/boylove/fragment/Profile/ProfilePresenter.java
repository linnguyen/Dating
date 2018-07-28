package com.example.lin.boylove.fragment.Profile;

import com.example.lin.boylove.entity.Response.User;

/**
 * Created by ryne on 27/10/2017.
 */

public interface ProfilePresenter {

    interface OnProfileFinishedListener {
        void onSuccess(User user);

        void onFailure(String message);
    }

    void getUserProfile();
}
