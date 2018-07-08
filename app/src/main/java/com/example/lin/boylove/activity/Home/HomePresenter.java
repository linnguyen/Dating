package com.example.lin.boylove.activity.Home;

/**
 * Created by lin on 18/09/2017.
 */

public interface HomePresenter {
    interface OnHomeFinishedListener {
        void onSuccess(String message);

        void onFailure(String message);
    }

    void logout();
}
