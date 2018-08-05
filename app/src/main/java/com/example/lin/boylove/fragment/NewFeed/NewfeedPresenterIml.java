package com.example.lin.boylove.fragment.NewFeed;

import android.content.Context;

import com.example.lin.boylove.entity.Response.ListNewFeed;
import com.example.lin.boylove.entity.Response.NewFeed;
import com.example.lin.boylove.entity.Response.User;
import com.example.lin.boylove.fragment.Profile.ProfileView;

/**
 * Created by ryne on 27/10/2017.
 */

public class NewfeedPresenterIml implements NewfeedPresenter,
        NewfeedPresenter.OnNewfeedFinishedListener {
    private Context context;
    private NewfeedView view;
    private NewfeedInteractor interactor;

    public NewfeedPresenterIml(Context context, NewfeedView view) {
        this.context = context;
        this.view = view;
        interactor = new NewfeedInteractorIml(context);
    }

    @Override
    public void onSuccess(ListNewFeed lstNewFeed) {
        view.showProgressBar();
        view.onGetNewFeedsSuccess(lstNewFeed);
    }

    @Override
    public void onFailure(String message) {
        view.hideProgressBar();
        view.showMessage(message);
    }

    @Override
    public void getNewFeeds() {
        view.showProgressBar();
        interactor.getNewFeeds(this);
    }
}
