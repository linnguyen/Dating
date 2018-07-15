package com.example.lin.boylove.fragment.Chat.adapter;
import com.example.lin.boylove.custom.commons.models.IMessage;
import com.example.lin.boylove.custom.commons.models.IUser;

import java.util.List;

/**
 * For implementing by real dialog model
 */

public interface IChatRoom<MESSAGE extends IMessage> {

    int getId();

    String getDialogPhoto();

    String getDialogName();

    List<? extends IUser> getUsers();

    MESSAGE getLastMessage();

    void setLastMessage(MESSAGE message);

    int getUnreadCount();
}
