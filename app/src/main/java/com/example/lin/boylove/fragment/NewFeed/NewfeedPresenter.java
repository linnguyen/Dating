package com.example.lin.boylove.fragment.NewFeed;

import com.example.lin.boylove.entity.Response.NewFeed;

/**
 * Created by ryne on 27/10/2017.
 */

public interface NewfeedPresenter {

    interface OnNewfeedFinishedListener {
        void onSuccess(NewFeed newFeed);

        void onFailure(String message);
    }

    void getNewFeeds();
}
