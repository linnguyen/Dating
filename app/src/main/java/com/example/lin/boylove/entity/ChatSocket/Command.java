package com.example.lin.boylove.entity.ChatSocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;

/**
 * Created by lin on 27/05/2018.
 */

public class Command {
    private static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    @Expose
    @SuppressWarnings("unused")
    private final String command;

    @Expose
    @SuppressWarnings("unused")
    private final String identifier;

    @Expose
    @SuppressWarnings("unused")
    private final String data;

    private Command(String command, String identifier) {
        this(command, identifier, null);
    }

    private Command(String command, String identifier, String data) {
        this.command = command;
        this.identifier = identifier;
        this.data = data;
    }

    public static Command subscribe(String identifier) {
        return new Command("subscribe", identifier);
    }

    public static Command unsubscribe(String identifier) {
        return new Command("unsubscribe", identifier);
    }

    public static Command message(String identifier, JsonObject params) {
        return new Command("message", identifier, params.toString());
    }

    public String toJson() {
        return GSON.toJson(this);
    }
}
