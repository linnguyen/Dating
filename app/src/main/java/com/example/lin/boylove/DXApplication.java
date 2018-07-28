package com.example.lin.boylove;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;

import com.example.lin.boylove.activity.Chat.ChatActivity;
import com.example.lin.boylove.entity.ChatSocket.Channel;
import com.example.lin.boylove.entity.ChatSocket.Command;
import com.example.lin.boylove.entity.Response.ChatMessage;
import com.example.lin.boylove.fragment.Chat.ChatRoomFragment;
import com.example.lin.boylove.localstorage.SessionManager;
import com.example.lin.boylove.service.WebSocketClient;
import com.example.lin.boylove.utilities.Constant;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class DXApplication extends android.app.Application {
    /**
     * Font Style
     */
    public static Typeface dxFontStyle;
    /**
     * DXApplication context
     */
    private Context context;

    /**
     * Get DXApplication instance
     *
     * @param context the context
     * @return DXApplication
     */
    public static DXApplication get(Context context) {
        return (DXApplication) context.getApplicationContext();
    }

    /*
     * Websocket
     */
    private WebSocketClient socket;

    /*
     * Channel
     */
    private Channel channel;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        // register to be informed of activities starting up
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                // new activity created; force its orientation to portrait
                // Todo, handle this if allow the app rotate
                activity.setRequestedOrientation(
                        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/ProximaNova-Regular.ttf");
    }

    /**
     * Get the context of application
     *
     * @return context
     */
    public Context getContext() {
        return context;
    }


    static class FontsOverride {
        static void setDefaultFont(Context context, String staticTypefaceFieldName, String fontAssetName) {
//            dxFontStyle = Typeface.createFromAsset(context.getAssets(),
//                    fontAssetName);
//            replaceFont(staticTypefaceFieldName, dxFontStyle);
        }

//        static void replaceFont(String staticTypefaceFieldName, final Typeface newTypeface) {
//            try {
//                final Field staticField = Typeface.class
//                        .getDeclaredField(staticTypefaceFieldName);
//                staticField.setAccessible(true);
//                staticField.set(null, newTypeface);
//            } catch (NoSuchFieldException | IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void connectToWebsocket() {
        try {
            socket = new WebSocketClient(new
                    URI(Constant.Config.SOCKET_URL + SessionManager.getInstance(getApplicationContext()).getToken()));
            socket.setListener(new WebSocketClient.WebSocketListener() {
                @Override
                public void onMessageResponse(final ChatMessage message) {

                    if (ChatActivity.instance != null) {
                        ChatActivity.instance.setMessageResponse(message);
                    }

                    if (ChatRoomFragment.instance != null) {
                        ChatRoomFragment.instance.setMessageResponse(message);
                    }

                    if (message.isFirstMessage()){
                        // if this is a new message of chat room,
                        // user need to re-subribe the channel to update the stream from channel
                        new Thread(new Runnable() {
                            public void run() {
                                unsubscribeChannel("RoomChannel");
                                subcribeChannel("RoomChannel");
                            }
                        }).start();
                    }
                }

                @Override
                public void onConnectSuccess() {
                    // subcribe to channel
                    subcribeChannel("RoomChannel");
//                    subcribeChannel("ChatChannel");
                }
            });
            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String content, int roomId, List<Integer> lstOtherUser) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("content", content);
        jsonObject.addProperty("room_id", roomId);
        JsonArray arrJsonOtherUser = new JsonArray();
        for (int other_user_id : lstOtherUser) {
            arrJsonOtherUser.add(other_user_id);
        }
        jsonObject.add("other_users", arrJsonOtherUser);
        Command command = Command.message(channel.toIdentifier(), jsonObject);
        socket.send(command.toJson());
    }

    public void subcribeChannel(String subcribedChannel) {
        // RoomChannel
        channel = new Channel(subcribedChannel);
        Command command = Command.subscribe(channel.toIdentifier());
        socket.send(command.toJson());
    }

    public void unsubscribeChannel(String unsubscribeChannel){
        // RoomChannel
        channel = new Channel(unsubscribeChannel);
        Command command = Command.unsubscribe(channel.toIdentifier());
        socket.send(command.toJson());
    }
}
