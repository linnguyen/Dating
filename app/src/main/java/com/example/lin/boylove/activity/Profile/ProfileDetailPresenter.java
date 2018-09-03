package com.example.lin.boylove.activity.Profile;

import com.example.lin.boylove.entity.Response.ChatRoom;
import com.example.lin.boylove.entity.Response.ListChatMessage;

/**
 * Created by lin on 18/09/2017.
 */

public interface ProfileDetailPresenter {
    interface OnChatFinishedListener {
        void onPrivateRoomSuccess(ChatRoom room);

        void onGetLstMessageSuccess(ListChatMessage messagesRoom);

        void onFailure(String message);
    }

    void getMessagesByRoom(int chatRoomId);

    void getMessagesForPrivateRoom(int otherUserId);

    void getPrivateRoom(int otherUserId);
}
