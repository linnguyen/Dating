package com.example.lin.boylove.fragment.Online;

import com.example.lin.boylove.entity.Response.Income;
import com.example.lin.boylove.entity.Response.Online;

import java.util.List;

/**
 * Created by ryne on 27/10/2017.
 */

public interface OnlinePresenter {

    interface OnOnlineFinishedListener {
        void onGetOnlineSuccess(List<Online> lstOnline);

        void onGetOnlineFail(String message);
    }

    void getListIncome();
}
