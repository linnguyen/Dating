package com.example.lin.boylove.activity.Profile;

/**
 * Created by lin on 19/09/2017.
 */

public interface ProfileDetailInteractor {
    void getChatMessagesByRoom(int chatRoomId, ProfileDetailPresenter.OnChatFinishedListener listener);

    void getMessagesForPrivateRoom(int otherUserId, ProfileDetailPresenter.OnChatFinishedListener listener);

    void getPrivateChatRoom(int otherUserId, ProfileDetailPresenter.OnChatFinishedListener listener);
}
