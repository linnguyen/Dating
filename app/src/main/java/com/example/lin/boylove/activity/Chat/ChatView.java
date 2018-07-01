package com.example.lin.boylove.activity.Chat;

import com.example.lin.boylove.entity.Response.ChatMessage;

import java.util.List;

/**
 * Created by lin on 18/09/2017.
 */

public interface ChatView {
    void onGetMessagesSuccess(List<ChatMessage> lstChatMessage);

    void showProgress();

    void showMessage(String message);

    void hideProgress();
}
