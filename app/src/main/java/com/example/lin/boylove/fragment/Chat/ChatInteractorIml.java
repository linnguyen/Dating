package com.example.lin.boylove.fragment.Chat;

import android.content.Context;

import com.example.lin.boylove.R;
import com.example.lin.boylove.entity.Response.Error;
import com.example.lin.boylove.entity.Response.Online;
import com.example.lin.boylove.fragment.Online.OnlineInteractor;
import com.example.lin.boylove.fragment.Online.OnlinePresenter;
import com.example.lin.boylove.service.DolaxAPIs;
import com.example.lin.boylove.utilities.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ryne on 27/10/2017.
 */

public class ChatInteractorIml implements OnlineInteractor {
    private Context context;
    private DolaxAPIs dolaxAPIs;

    public ChatInteractorIml(Context context) {
        this.context = context;
        dolaxAPIs = DolaxAPIs.Factory.create();
    }

    @Override
    public void getListOnline(final OnlinePresenter.OnOnlineFinishedListener listener) {
        Call<List<Online>> call = dolaxAPIs.getOnlines();
        call.enqueue(new Callback<List<Online>>() {
            @Override
            public void onResponse(Call<List<Online>> call, Response<List<Online>> response) {
                if (response.isSuccessful()) {
                    List<Online> lstOnline = response.body();
                    if (lstOnline != null) {
                        listener.onSuccess(lstOnline);
                    }
                } else {
                    Error error = DolaxAPIs.Factory.getError(response.errorBody());
                    listener.onFailure(error.getErrors());
                }
            }

            @Override
            public void onFailure(Call<List<Online>> call, Throwable t) {
                Utils.showToast(context, context.getString(R.string.toast_st_went_wrong));
            }
        });
    }
}
