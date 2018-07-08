package com.example.lin.boylove.entity.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lin on 13/05/2018.
 */

public class ListChatRoom {
    @SerializedName("rooms")
    @Expose
    private List<ChatRoom> lstChatRoom;

    @SerializedName("meta")
    @Expose
    private Pagination pagination;

    public List<ChatRoom> getLstChatRoom() {
        return lstChatRoom;
    }

    public void setLstChatRoom(List<ChatRoom> lstChatRoom) {
        this.lstChatRoom = lstChatRoom;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
