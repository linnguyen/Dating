package com.example.lin.boylove.fragment.Chat;

import android.content.Context;

import com.example.lin.boylove.entity.Response.Online;
import com.example.lin.boylove.fragment.Online.OnlineInteractor;
import com.example.lin.boylove.fragment.Online.OnlineInteractorIml;
import com.example.lin.boylove.fragment.Online.OnlinePresenter;
import com.example.lin.boylove.fragment.Online.OnlineView;

import java.util.List;

/**
 * Created by ryne on 27/10/2017.
 */

public class ChatPresenterIml implements OnlinePresenter,
        OnlinePresenter.OnOnlineFinishedListener {
    private Context context;
    private OnlineView view;
    private OnlineInteractor interactor;

    public ChatPresenterIml(Context context, OnlineView view) {
        this.context = context;
        this.view = view;
        interactor = new OnlineInteractorIml(context);
    }

    @Override
    public void getLstOnline() {
        view.showProgressBar();
        interactor.getListOnline(this);
    }

    @Override
    public void onSuccess(List<Online> lstOnline) {
        view.hideProgressBar();
        view.getListOnlineSuccess(lstOnline);
    }

    @Override
    public void onFailure(String message) {
        view.hideProgressBar();
        view.showMessage(message);
    }
}
