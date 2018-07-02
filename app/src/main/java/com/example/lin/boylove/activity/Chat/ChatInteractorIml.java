package com.example.lin.boylove.activity.Chat;

import android.content.Context;

import com.example.lin.boylove.R;
import com.example.lin.boylove.entity.Response.ChatMessage;
import com.example.lin.boylove.entity.Response.Error;
import com.example.lin.boylove.localstorage.SessionManager;
import com.example.lin.boylove.service.DolaxAPIs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lin on 19/09/2017.
 */

public class ChatInteractorIml implements ChatInteractor {
    private Context context;
    private DolaxAPIs dolaxAPIs;

    public ChatInteractorIml(Context context) {
        this.context = context;
        dolaxAPIs = DolaxAPIs.Factory.create();
    }

    @Override
    public void getChatMessagesByRoom(int chatRoomId, final ChatPresenter.OnChatFinishedListener listener) {
        Call<List<ChatMessage>> call = dolaxAPIs.getMessagesByRoom(
                SessionManager.getInstance(context).getToken(), chatRoomId);
        call.enqueue(new Callback<List<ChatMessage>>() {
            @Override
            public void onResponse(Call<List<ChatMessage>> call, Response<List<ChatMessage>> response) {
                if (response.isSuccessful()) {
                    List<ChatMessage> lstChatMessage = response.body();
                    if (lstChatMessage != null) {
                        listener.onSuccess(lstChatMessage);
                    }
                } else {
                    Error error = DolaxAPIs.Factory.getError(response.errorBody());
                    listener.onFailure(error.getErrors());
                }
            }

            @Override
            public void onFailure(Call<List<ChatMessage>> call, Throwable t) {
                listener.onFailure(context.getString(R.string.toast_login_fail));
            }
        });
    }

    @Override
    public void getMessagesForPrivateRoom(int otherUserId, final ChatPresenter.OnChatFinishedListener listener) {
        Call<List<ChatMessage>> call = dolaxAPIs.getPrivateMessages(
                SessionManager.getInstance(context).getToken(), otherUserId);
        call.enqueue(new Callback<List<ChatMessage>>() {
            @Override
            public void onResponse(Call<List<ChatMessage>> call, Response<List<ChatMessage>> response) {
                if (response.isSuccessful()) {
                    List<ChatMessage> lstChatMessage = response.body();
                    if (lstChatMessage != null) {
                        listener.onSuccess(lstChatMessage);
                    }
                } else {
                    Error error = DolaxAPIs.Factory.getError(response.errorBody());
                    listener.onFailure(error.getErrors());
                }
            }

            @Override
            public void onFailure(Call<List<ChatMessage>> call, Throwable t) {
                listener.onFailure(context.getString(R.string.toast_login_fail));
            }
        });
    }
}
