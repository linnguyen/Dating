package com.example.lin.boylove.fragment.Online;

import android.content.Context;

import com.example.lin.boylove.R;
import com.example.lin.boylove.entity.Response.Error;
import com.example.lin.boylove.entity.Response.Online;
import com.example.lin.boylove.entity.Response.User;
import com.example.lin.boylove.localstorage.SessionManager;
import com.example.lin.boylove.service.DolaxAPIs;
import com.example.lin.boylove.utilities.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ryne on 27/10/2017.
 */

public class OnlineInteractorIml implements OnlineInteractor {
    private Context context;
    private DolaxAPIs dolaxAPIs;

    public OnlineInteractorIml(Context context) {
        this.context = context;
        dolaxAPIs = DolaxAPIs.Factory.create();
    }

    @Override
    public void getListOnline(final OnlinePresenter.OnOnlineFinishedListener listener) {
        Call<Online> call = dolaxAPIs.getOnlines(SessionManager.getInstance(context).getToken());
        call.enqueue(new Callback<Online>() {
            @Override
            public void onResponse(Call<Online> call, Response<Online> response) {
                if (response.isSuccessful()) {
                    Online online = response.body();
                    if (online != null) {
                        listener.onSuccess(online);
                    }
                } else {
                    Error error = DolaxAPIs.Factory.getError(response.errorBody());
                    listener.onFailure(error.getErrors());
                }
            }

            @Override
            public void onFailure(Call<Online> call, Throwable t) {
                Utils.showToast(context, context.getString(R.string.toast_st_went_wrong));
            }
        });
    }
}
