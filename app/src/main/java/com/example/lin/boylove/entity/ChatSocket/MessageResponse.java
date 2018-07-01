package com.example.lin.boylove.entity.ChatSocket;

import com.example.lin.boylove.entity.Response.ChatMessage;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 * Created by lin on 27/05/2018.
 */

public class MessageResponse {
    private static final Gson GSON = new Gson();

    @SuppressWarnings("unused")
    private String identifier;

    @SuppressWarnings("unused")
    private String type;

    @SuppressWarnings("unused")
    private JsonElement message;

    public static MessageResponse fromJson(String json) {
        return GSON.fromJson(json, MessageResponse.class);
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getType() {
        return type;
    }

    public JsonElement getMessage() {
        return message;
    }

    public boolean isWelcome() {
        return "welcome".equals(getType());
    }

    public boolean isPing() {
        return "ping".equals(getType());
    }

    public boolean isConfirmation() {
        return "confirm_subscription".equals(getType());
    }

    public boolean isRejection() {
        return "reject_subscription".equals(getType());
    }

    public ChatMessage getMessageObject() {
        return GSON.fromJson(getMessage(), ChatMessage.class);
    }
}
