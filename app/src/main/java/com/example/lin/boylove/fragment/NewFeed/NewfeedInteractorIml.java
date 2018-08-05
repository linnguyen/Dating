package com.example.lin.boylove.fragment.NewFeed;

import android.content.Context;

import com.example.lin.boylove.R;
import com.example.lin.boylove.entity.Response.Error;
import com.example.lin.boylove.entity.Response.ListNewFeed;
import com.example.lin.boylove.entity.Response.User;
import com.example.lin.boylove.localstorage.SessionManager;
import com.example.lin.boylove.service.DolaxAPIs;
import com.example.lin.boylove.utilities.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ryne on 27/10/2017.
 */

public class NewfeedInteractorIml implements NewfeedInteractor {
    private Context context;
    private DolaxAPIs dolaxAPIs;

    public NewfeedInteractorIml(Context context) {
        this.context = context;
        dolaxAPIs = DolaxAPIs.Factory.create();
    }

    @Override
    public void getNewFeeds(final NewfeedPresenter.OnNewfeedFinishedListener listener) {
        Call<User> call = dolaxAPIs.getUserProfile(SessionManager.getInstance(context).getToken(), 10);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User lstNewFeed = response.body();
                    if (lstNewFeed != null) {
//                        listener.onSuccess(lstNewFeed);
                    }
                } else {
                    Error error = DolaxAPIs.Factory.getError(response.errorBody());
                    listener.onFailure(error.getErrors());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        //        call.enqueue(new Callback<ListNewFeed>() {
//            @Override
//            public void onResponse(Call<ListNewFeed> call, Response<ListNewFeed> response) {
//                if (response.isSuccessful()) {
//                    ListNewFeed lstNewFeed = response.body();
//                    if (lstNewFeed != null) {
//                        listener.onSuccess(lstNewFeed);
//                    }
//                } else {
//                    Error error = DolaxAPIs.Factory.getError(response.errorBody());
//                    listener.onFailure(error.getErrors());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ListNewFeed> call, Throwable t) {
//                Utils.showToast(context, context.getString(R.string.toast_st_went_wrong));
//            }
//        });
    }
}
