package com.example.lin.boylove.entity.ChatSocket;

/**
 * Created by lin on 26/05/2018.
 */

public class Identifier {
    private String channel;

    private String room;

    public Identifier(String channel, String room) {
        this.channel = channel;
        this.room = room;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
