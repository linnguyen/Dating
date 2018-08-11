package com.example.lin.boylove.activity.Settings;

import android.content.Context;

import com.example.lin.boylove.entity.Response.User;
import com.example.lin.boylove.localstorage.SessionManager;
import com.example.lin.boylove.service.DolaxAPIs;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lin on 19/09/2017.
 */

public class SettingInteractorIml implements SettingInteractor {
    private Context context;
    private DolaxAPIs dolaxAPIs;

    public SettingInteractorIml(Context context) {
        this.context = context;
        dolaxAPIs = DolaxAPIs.Factory.create();
    }

    @Override
    public void logout() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", SessionManager.getInstance(context).getToken());
        Call<User> call = dolaxAPIs.logout(jsonObject);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // no need to handle response for logout
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
