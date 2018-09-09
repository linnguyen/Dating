package com.example.lin.boylove.activity.Profile;

import android.content.Context;

import com.example.lin.boylove.R;
import com.example.lin.boylove.entity.Response.ChatRoom;
import com.example.lin.boylove.entity.Response.Error;
import com.example.lin.boylove.entity.Response.ListChatMessage;
import com.example.lin.boylove.localstorage.SessionManager;
import com.example.lin.boylove.service.DolaxAPIs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lin on 19/09/2017.
 */

public class ProfileDetailInteractorIml implements ProfileDetailInteractor {
    private Context context;
    private DolaxAPIs dolaxAPIs;

    public ProfileDetailInteractorIml(Context context) {
        this.context = context;
        dolaxAPIs = DolaxAPIs.Factory.create();
    }
}
