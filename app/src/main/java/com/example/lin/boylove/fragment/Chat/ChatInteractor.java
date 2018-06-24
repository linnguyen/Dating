package com.example.lin.boylove.fragment.Chat;

import com.example.lin.boylove.fragment.Online.OnlinePresenter;

/**
 * Created by ryne on 27/10/2017.
 */

public interface ChatInteractor {
    void getListOnline(OnlinePresenter.OnOnlineFinishedListener listener);
}
