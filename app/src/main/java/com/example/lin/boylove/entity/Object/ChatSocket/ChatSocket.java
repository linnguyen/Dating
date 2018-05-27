package com.example.lin.boylove.entity.Object.ChatSocket;

/**
 * Created by lin on 26/05/2018.
 */

public class ChatSocket {
    private String command;

    private Identifier identifier;

    private Data data;

    public ChatSocket(String command, Identifier identifier) {
        this.command = command;
        this.identifier = identifier;
    }

    public ChatSocket(String command, Identifier identifier, Data data) {
        this.command = command;
        this.identifier = identifier;
        this.data = data;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
