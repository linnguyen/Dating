package com.example.lin.boylove.activity.Chat;

import android.content.Context;

import com.example.lin.boylove.R;
import com.example.lin.boylove.entity.Response.ChatRoom;
import com.example.lin.boylove.entity.Response.Error;
import com.example.lin.boylove.entity.Response.MessagesRoom;
import com.example.lin.boylove.localstorage.SessionManager;
import com.example.lin.boylove.service.DolaxAPIs;

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
        Call<MessagesRoom> call = dolaxAPIs.getMessagesByRoom(
                SessionManager.getInstance(context).getToken(), chatRoomId);
        call.enqueue(new Callback<MessagesRoom>() {
            @Override
            public void onResponse(Call<MessagesRoom> call, Response<MessagesRoom> response) {
                if (response.isSuccessful()) {
                    MessagesRoom messagesRoom = response.body();
                    if (messagesRoom != null) {
                        listener.onGetLstMessageSuccess(messagesRoom);
                    }
                } else {
                    Error error = DolaxAPIs.Factory.getError(response.errorBody());
                    listener.onFailure(error.getErrors());
                }
            }

            @Override
            public void onFailure(Call<MessagesRoom> call, Throwable t) {
                listener.onFailure(context.getString(R.string.toast_login_fail));
            }
        });
    }

    @Override
    public void getMessagesForPrivateRoom(int otherUserId, final ChatPresenter.OnChatFinishedListener listener) {
        Call<MessagesRoom> call = dolaxAPIs.getPrivateMessages(
                SessionManager.getInstance(context).getToken(), otherUserId);
        call.enqueue(new Callback<MessagesRoom>() {
            @Override
            public void onResponse(Call<MessagesRoom> call, Response<MessagesRoom> response) {
                if (response.isSuccessful()) {
                    MessagesRoom messagesRoom = response.body();
                    if (messagesRoom != null) {
                        listener.onGetLstMessageSuccess(messagesRoom);
                    }
                } else {
                    Error error = DolaxAPIs.Factory.getError(response.errorBody());
                    listener.onFailure(error.getErrors());
                }
            }

            @Override
            public void onFailure(Call<MessagesRoom> call, Throwable t) {
                listener.onFailure(context.getString(R.string.toast_login_fail));
            }
        });
    }

    @Override
    public void getPrivateChatRoom(int otherUserId, final ChatPresenter.OnChatFinishedListener listener) {
        Call<ChatRoom> call = dolaxAPIs.getPrivateRoom(
                SessionManager.getInstance(context).getToken(), otherUserId);
        call.enqueue(new Callback<ChatRoom>() {
            @Override
            public void onResponse(Call<ChatRoom> call, Response<ChatRoom> response) {
                if (response.isSuccessful()) {
                    ChatRoom chatRoom = response.body();
                    if (chatRoom != null) {
                        listener.onPrivateRoomSuccess(chatRoom);
                    }
                } else {
                    Error error = DolaxAPIs.Factory.getError(response.errorBody());
                    listener.onFailure(error.getErrors());
                }
            }

            @Override
            public void onFailure(Call<ChatRoom> call, Throwable t) {
                listener.onFailure(context.getString(R.string.toast_login_fail));
            }
        });
    }
}
