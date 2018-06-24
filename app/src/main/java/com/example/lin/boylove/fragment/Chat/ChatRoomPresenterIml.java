package com.example.lin.boylove.fragment.Chat;

import android.content.Context;

import com.example.lin.boylove.entity.Response.ChatRoom;

import java.util.List;

/**
 * Created by ryne on 27/10/2017.
 */

public class ChatRoomPresenterIml implements ChatRoomPresenter,
        ChatRoomPresenter.OnChatFinishedListener {
    private Context context;
    private ChatRoomView view;
    private ChatRoomInteractor interactor;

    public ChatRoomPresenterIml(Context context, ChatRoomView view) {
        this.context = context;
        this.view = view;
        interactor = new ChatRoomInteractorIml(context);
    }

    @Override
    public void getLstChatRoom() {
        view.showProgressBar();
        interactor.getLstChatRoom(this);
    }

    @Override
    public void onSuccess(List<ChatRoom> lstChatRoom) {
        view.hideProgressBar();
        view.getLstChatRoomSuccess(lstChatRoom);
    }

    @Override
    public void onFailure(String message) {
        view.hideProgressBar();
        view.showMessage(message);
    }
}
