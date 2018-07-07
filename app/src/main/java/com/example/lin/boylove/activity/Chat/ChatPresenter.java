package com.example.lin.boylove.activity.Chat;

import com.example.lin.boylove.entity.Response.ChatMessage;
import com.example.lin.boylove.entity.Response.ChatRoom;
import com.example.lin.boylove.entity.Response.MessagesRoom;

import java.util.List;

/**
 * Created by lin on 18/09/2017.
 */

public interface ChatPresenter {
    interface OnChatFinishedListener {
        void onPrivateRoomSuccess(ChatRoom room);

        void onGetLstMessageSuccess(MessagesRoom messagesRoom);

        void onFailure(String message);
    }

    void getMessagesByRoom(int chatRoomId);

    void getMessagesForPrivateRoom(int otherUserId);

    void getPrivateRoom(int otherUserId);
}
