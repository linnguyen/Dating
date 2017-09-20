package com.example.lin.dollar.activity.Login;

import android.util.Log;

import com.example.lin.dollar.Entity.Response.User;
import com.example.lin.dollar.Service.DolaxAPIs;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lin on 19/09/2017.
 */

public class LoginInteractorIml implements LoginInteractor {
    private DolaxAPIs dolaxAPIs;

    public LoginInteractorIml() {
        dolaxAPIs = DolaxAPIs.Factory.create();
    }

    @Override
    public void login(String email, String password, final LoginPresenter.OnLoginFinishedListener listener) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("password", password);
        Call<User> call = dolaxAPIs.login(jsonObject);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    if (user != null) {
                        listener.onSuccess();
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
