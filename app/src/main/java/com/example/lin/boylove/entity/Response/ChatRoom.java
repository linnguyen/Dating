package com.example.lin.boylove.entity.Response;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.lin.boylove.custom.commons.models.IMessage;
import com.example.lin.boylove.custom.commons.models.IUser;
import com.example.lin.boylove.fragment.Chat.adapter.IChatRoom;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ryne on 20/09/2017.
 */

public class ChatRoom implements Parcelable, IChatRoom {
    private int id;

    private String topic;

    private String created_at;

    private String updated_at;

    @SerializedName("last_message")
    @Expose
    private ChatMessage lastMessage;


    protected ChatRoom(Parcel in) {
        id = in.readInt();
        topic = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
    }

    public static final Creator<ChatRoom> CREATOR = new Creator<ChatRoom>() {
        @Override
        public ChatRoom createFromParcel(Parcel in) {
            return new ChatRoom(in);
        }

        @Override
        public ChatRoom[] newArray(int size) {
            return new ChatRoom[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(topic);
        parcel.writeString(created_at);
        parcel.writeString(updated_at);
    }


//    public int getId() {
//        return id;
//    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getDialogPhoto() {
        return null;
    }

    @Override
    public String getDialogName() {
        return topic;
    }

    @Override
    public List<? extends IUser> getUsers() {
        return null;
    }

    @Override
    public IMessage getLastMessage() {
        return lastMessage;
    }

    @Override
    public void setLastMessage(IMessage message) {

    }

    @Override
    public int getUnreadCount() {
        return 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
