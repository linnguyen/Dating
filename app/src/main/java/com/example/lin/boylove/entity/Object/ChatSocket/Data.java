package com.example.lin.boylove.entity.Object.ChatSocket;

/**
 * Created by lin on 26/05/2018.
 */

public class Data {
    private String action;

    private String message;

    public Data(String action, String message) {
        this.action = action;
        this.message = message;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
