package com.example.lin.boylove.activity.Chat;

import android.content.Context;

import com.example.lin.boylove.entity.Response.ChatRoom;
import com.example.lin.boylove.entity.Response.ListChatMessage;

/**
 * Created by lin on 19/09/2017.
 */

public class ChatPresenterIml implements ChatPresenter, ChatPresenter.OnChatFinishedListener {
    private ChatView view;
    private ChatInteractor interactor;
    private Context context;

    public ChatPresenterIml(ChatView view, Context context) {
        this.context = context;
        this.view = view;
        this.interactor = new ChatInteractorIml(context);
    }

    @Override
    public void onPrivateRoomSuccess(ChatRoom room) {
        view.hideProgress();
        view.onGetPrivateRoomSucess(room);
    }

    @Override
    public void onGetLstMessageSuccess(ListChatMessage messagesRoom) {
        view.hideProgress();
        view.onGetMessagesSuccess(messagesRoom);
    }

    @Override
    public void onFailure(String message) {
        view.hideProgress();
        view.showMessage(message);
    }

    @Override
    public void getMessagesByRoom(int chatRoomId) {
        interactor.getChatMessagesByRoom(chatRoomId, this);
    }

    @Override
    public void getMessagesForPrivateRoom(int otherUserId) {
        interactor.getMessagesForPrivateRoom(otherUserId, this);
    }

    @Override
    public void getPrivateRoom(int otherUserId) {
        interactor.getPrivateChatRoom(otherUserId, this);
    }
}
