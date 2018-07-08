package com.example.lin.boylove.fragment.Chat;

import com.example.lin.boylove.entity.Response.ChatRoom;
import com.example.lin.boylove.entity.Response.ListChatRoom;
import com.example.lin.boylove.entity.Response.Online;

import java.util.List;

/**
 * Created by ryne on 27/10/2017.
 */

public interface ChatRoomView {
    void showProgressBar();

    void hideProgressBar();

    void showMessage(String message);

    void getLstChatRoomSuccess(ListChatRoom lstChatRoom);
}
