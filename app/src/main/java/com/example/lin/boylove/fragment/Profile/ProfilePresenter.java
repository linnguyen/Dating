package com.example.lin.boylove.fragment.Profile;

import com.example.lin.boylove.entity.Response.Online;

/**
 * Created by ryne on 27/10/2017.
 */

public interface ProfilePresenter {

    interface OnOnlineFinishedListener {
        void onSuccess(Online online);

        void onFailure(String message);
    }

    void getLstOnline();
}
