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

    @Override
    public void getChatMessagesByRoom(int chatRoomId, final ProfileDetailPresenter.OnChatFinishedListener listener) {
        Call<ListChatMessage> call = dolaxAPIs.getMessagesByRoom(
                SessionManager.getInstance(context).getToken(), chatRoomId);
        call.enqueue(new Callback<ListChatMessage>() {
            @Override
            public void onResponse(Call<ListChatMessage> call, Response<ListChatMessage> response) {
                if (response.isSuccessful()) {
                    ListChatMessage messagesRoom = response.body();
                    if (messagesRoom != null) {
                        listener.onGetLstMessageSuccess(messagesRoom);
                    }
                } else {
                    Error error = DolaxAPIs.Factory.getError(response.errorBody());
                    listener.onFailure(error.getErrors());
                }
            }

            @Override
            public void onFailure(Call<ListChatMessage> call, Throwable t) {
                listener.onFailure(context.getString(R.string.toast_login_fail));
            }
        });
    }

    @Override
    public void getMessagesForPrivateRoom(int otherUserId, final ProfileDetailPresenter.OnChatFinishedListener listener) {
        Call<ListChatMessage> call = dolaxAPIs.getPrivateMessages(
                SessionManager.getInstance(context).getToken(), otherUserId);
        call.enqueue(new Callback<ListChatMessage>() {
            @Override
            public void onResponse(Call<ListChatMessage> call, Response<ListChatMessage> response) {
                if (response.isSuccessful()) {
                    ListChatMessage messagesRoom = response.body();
                    if (messagesRoom != null) {
                        listener.onGetLstMessageSuccess(messagesRoom);
                    }
                } else {
                    Error error = DolaxAPIs.Factory.getError(response.errorBody());
                    listener.onFailure(error.getErrors());
                }
            }

            @Override
            public void onFailure(Call<ListChatMessage> call, Throwable t) {
                listener.onFailure(context.getString(R.string.toast_login_fail));
            }
        });
    }

    @Override
    public void getPrivateChatRoom(int otherUserId, final ProfileDetailPresenter.OnChatFinishedListener listener) {
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
