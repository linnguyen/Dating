package com.example.lin.boylove.activity.Chat;

/**
 * Created by lin on 19/09/2017.
 */

public interface ChatInteractor {
    void getChatMessagesByRoom(int chatRoomId, ChatPresenter.OnChatFinishedListener listener);
}
