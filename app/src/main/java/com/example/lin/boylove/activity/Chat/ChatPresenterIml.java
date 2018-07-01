package com.example.lin.boylove.activity.Chat;

import android.content.Context;

import com.example.lin.boylove.entity.Response.ChatMessage;

import java.util.List;

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
    public void onSuccess(List<ChatMessage> lstChatMessage) {
        view.hideProgress();
        view.onGetMessagesSuccess(lstChatMessage);
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
}
