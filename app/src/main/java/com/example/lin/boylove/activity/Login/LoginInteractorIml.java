package com.example.lin.boylove.activity.Login;

import android.content.Context;

import com.example.lin.boylove.entity.Response.User;
import com.example.lin.boylove.R;
import com.example.lin.boylove.service.DolaxAPIs;
import com.example.lin.boylove.localstorage.SessionManager;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lin on 19/09/2017.
 */

public class LoginInteractorIml implements LoginInteractor {
    private Context context;
    private DolaxAPIs dolaxAPIs;

    public LoginInteractorIml(Context context) {
        this.context = context;
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
                        SessionManager sessionManager = SessionManager.getInstance(context);
                        sessionManager.setToken(user.getAuth_token());
                        listener.onSuccess(context.getString(R.string.toast_login_success));
                    } else {
                        listener.onFailure(context.getString(R.string.toast_login_fail));
                    }
                } else {
                    listener.onFailure(context.getString(R.string.toast_login_fail));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                  listener.onFailure(context.getString(R.string.toast_login_fail));
            }
        });
    }
}
