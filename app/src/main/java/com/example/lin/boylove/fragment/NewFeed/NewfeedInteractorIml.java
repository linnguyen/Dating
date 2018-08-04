package com.example.lin.boylove.fragment.NewFeed;

import android.content.Context;

import com.example.lin.boylove.R;
import com.example.lin.boylove.entity.Response.Error;
import com.example.lin.boylove.entity.Response.User;
import com.example.lin.boylove.fragment.Profile.ProfilePresenter;
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
    public void getNewFeeds(NewfeedPresenter.OnNewfeedFinishedListener listener) {

    }

//    @Override
//    public void getUserProfile(final ProfilePresenter.OnProfileFinishedListener listener) {
//        Call<User> call = dolaxAPIs.getUserProfile(
//                SessionManager.getInstance(context).getToken(),
//                SessionManager.getInstance(context).getUserId()
//        );
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                if (response.isSuccessful()) {
//                    User user = response.body();
//                    if (user != null) {
//                        listener.onSuccess(user);
//                    }
//                } else {
//                    Error error = DolaxAPIs.Factory.getError(response.errorBody());
//                    listener.onFailure(error.getErrors());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Utils.showToast(context, context.getString(R.string.toast_st_went_wrong));
//            }
//        });
//    }
}
