package com.example.lin.boylove.activity.Chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lin.boylove.R;
import com.example.lin.boylove.adapter.ChatAdapter;
import com.example.lin.boylove.entity.Object.ChatSocket.Channel;
import com.example.lin.boylove.entity.Object.ChatSocket.ChatSocket;
import com.example.lin.boylove.entity.Object.ChatSocket.Command;
import com.example.lin.boylove.entity.Object.ChatSocket.Data;
import com.example.lin.boylove.entity.Object.ChatSocket.Identifier;
import com.example.lin.boylove.service.WebSocketClient;
import com.example.lin.boylove.utilities.Constant;
import com.example.lin.boylove.utilities.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity
        implements WebSocketClient.WebSocketListener {
    @BindView(R.id.edt_message)
    public EditText edtMessage;
    @BindView(R.id.rcv_message_chat)
    public RecyclerView rcvChat;

    private WebSocketClient socket;
    private ChatAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
//        FirebaseAuth

        //load chat message here.F
//        AuthUI.getInstance().s
        adapter = new ChatAdapter(this);
        rcvChat.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rcvChat.setAdapter(adapter);
        List<String> lst = new ArrayList<>();
        lst.add("hehe");
        lst.add("sojd");
        adapter.setListData(lst);

        displayChatMessage();

        connectToWebsocket();

    }

    @OnClick(R.id.btn_send)
    public void onSendClick() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("content", "Gic gic");
        Channel channel = new Channel("ChatChannel", "1470155475503808512");
        Command commandSubcribe = Command.subscribe(channel.toIdentifier());
        socket.send(commandSubcribe.toJson());
        Command command = Command.message(channel.toIdentifier(), jsonObject);
        socket.send(command.toJson());
    }

    private void displayChatMessage() {

    }

    private void connectToWebsocket() {
        try {
            socket = new WebSocketClient(new URI(Constant.Config.SOCKET_URL));
            socket.setListener(this);
            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessageResponse(String message) {
//        Utils.showToast(ChatActivity.this, message.toString());
        adapter.setData("Hello" + Math.random());
//        rcvChat.scrollToPosition(adapter.getItemCount() - 1);

    }
}
