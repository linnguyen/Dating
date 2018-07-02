package com.example.lin.boylove.activity.Chat;

import com.example.lin.boylove.entity.Response.ChatMessage;

import java.util.List;

/**
 * Created by lin on 18/09/2017.
 */

public interface ChatPresenter {
    interface OnChatFinishedListener {
        void onSuccess(List<ChatMessage> lstChatMessage);

        void onFailure(String message);
    }

    void getMessagesByRoom(int chatRoomId);

    void getMessagesForPrivateRoom(int otherUserId);
}
