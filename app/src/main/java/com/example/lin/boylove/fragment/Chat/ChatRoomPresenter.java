package com.example.lin.boylove.fragment.Chat;

import com.example.lin.boylove.entity.Response.ChatRoom;
import com.example.lin.boylove.entity.Response.ListChatRoom;
import com.example.lin.boylove.entity.Response.Online;

import java.util.List;


public interface ChatRoomPresenter {

    interface OnChatFinishedListener {
        void onSuccess(ListChatRoom listChatRoom);

        void onFailure(String message);
    }

    void getLstChatRoom();
}
