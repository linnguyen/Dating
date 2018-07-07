package com.example.lin.boylove.entity.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lin on 13/05/2018.
 */

public class MessagesRoom  {
    @SerializedName("messages")
    @Expose
    private List<ChatMessage> lstMessage;

    @SerializedName("meta")
    @Expose
    private Pagination pagination;

    public List<ChatMessage> getLstMessage() {
        return lstMessage;
    }

    public void setLstMessage(List<ChatMessage> lstMessage) {
        this.lstMessage = lstMessage;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
