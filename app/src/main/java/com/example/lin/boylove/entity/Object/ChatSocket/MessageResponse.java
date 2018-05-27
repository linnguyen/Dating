package com.example.lin.boylove.entity.Object.ChatSocket;

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

    /*package*/ String getIdentifier() {
        return identifier;
    }

    /*package*/ String getType() {
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

    /*package*/ boolean isConfirmation() {
        return "confirm_subscription".equals(getType());
    }

    /*package*/ boolean isRejection() {
        return "reject_subscription".equals(getType());
    }
}
