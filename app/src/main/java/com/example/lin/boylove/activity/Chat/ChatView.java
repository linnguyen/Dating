package com.example.lin.boylove.activity.Chat;

import com.example.lin.boylove.entity.Response.ChatMessage;
import com.example.lin.boylove.entity.Response.ChatRoom;
import com.example.lin.boylove.entity.Response.MessagesRoom;

import java.util.List;

/**
 * Created by lin on 18/09/2017.
 */

public interface ChatView {
    void onGetMessagesSuccess(MessagesRoom messagesRoom);

    void onGetPrivateMessageSucess(ChatRoom room);

    void showProgress();

    void showMessage(String message);

    void hideProgress();
}
