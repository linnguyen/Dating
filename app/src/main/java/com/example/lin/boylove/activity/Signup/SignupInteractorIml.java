package com.example.lin.boylove.activity.Signup;

import android.content.Context;

import com.example.lin.boylove.R;
import com.example.lin.boylove.entity.Response.Error;
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

public class SignupInteractorIml implements SignupInteractor {
    private Context context;
    private DolaxAPIs dolaxAPIs;

    public SignupInteractorIml(Context context) {
        this.context = context;
        dolaxAPIs = DolaxAPIs.Factory.create();
    }

    @Override
    public void createAccount(String dolaxName, String email, String password, String confirmPassword, String imageUrl, final SignupPresenter.OnSignupFinishedListener listener) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_name", dolaxName);
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("password", password);
        jsonObject.addProperty("password_confirmation", confirmPassword);
        jsonObject.addProperty("image", imageUrl);
        Call<User> call = dolaxAPIs.createAccount(jsonObject);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    if (user != null) {
                        SessionManager sessionManager = SessionManager.getInstance(context);
                        sessionManager.setToken(user.getAuth_token());
                        sessionManager.setUserId(user.getId());
                        listener.onSuccess(context.getString(R.string.toast_register_success));
                    }
                } else {
                    Error error = DolaxAPIs.Factory.getError(response.errorBody());
                    listener.onFailure(error.getErrors());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onFailure(context.getString(R.string.toast_login_fail));
            }
        });
    }
}
