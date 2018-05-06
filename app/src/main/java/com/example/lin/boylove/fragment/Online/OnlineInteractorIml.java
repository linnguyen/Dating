package com.example.lin.boylove.fragment.Online;

import android.content.Context;

import com.example.lin.boylove.entity.Response.Online;
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
        Call<List<Online>> call = dolaxAPIs.getOnline();
        call.enqueue(new Callback<List<Online>>() {
            @Override
            public void onResponse(Call<List<Online>> call, Response<List<Online>> response) {
                if (response.isSuccessful()) {
                    List<Online> lstOnline = response.body();
                    if (lstOnline != null) {
                        listener.onGetOnlineSuccess(lstOnline);
                    } else {
                        Utils.showToast(context, "Fail");
                    }
                } else {
                    Utils.showToast(context, "Fail");
                }
            }

            @Override
            public void onFailure(Call<List<Online>> call, Throwable t) {
                Utils.showToast(context, "Fail");
            }
        });
    }
}
