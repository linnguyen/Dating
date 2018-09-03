package com.example.lin.boylove.activity.Profile;

import com.example.lin.boylove.entity.Response.ChatRoom;
import com.example.lin.boylove.entity.Response.ListChatMessage;

/**
 * Created by lin on 18/09/2017.
 */

public interface ProfileDetailView {
    void onGetMessagesSuccess(ListChatMessage messagesRoom);

    void onGetPrivateRoomSucess(ChatRoom room);

    void showProgress();

    void showMessage(String message);

    void hideProgress();
}
