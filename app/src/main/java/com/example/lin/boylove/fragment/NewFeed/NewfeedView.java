package com.example.lin.boylove.fragment.NewFeed;

import com.example.lin.boylove.entity.Response.ListNewFeed;
import com.example.lin.boylove.entity.Response.NewFeed;

/**
 * Created by ryne on 27/10/2017.
 */

public interface NewfeedView {
    void showProgressBar();

    void hideProgressBar();

    void showMessage(String message);

    void onGetNewFeedsSuccess(ListNewFeed lstNewFeed);
}
