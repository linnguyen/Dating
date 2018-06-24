package com.example.lin.boylove.activity.Chat;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.example.lin.boylove.DXApplication;
import com.example.lin.boylove.R;
import com.example.lin.boylove.activity.DxBaseActivity;
import com.example.lin.boylove.adapter.ChatAdapter;
import com.example.lin.boylove.utilities.Constant;
import com.example.lin.boylove.utilities.Utils;

import butterknife.BindView;
import butterknife.OnClick;

public class ChatActivity extends DxBaseActivity {
    @BindView(R.id.edt_message)
    public EditText edtMessage;
    @BindView(R.id.rcv_message_chat)
    public RecyclerView rcvChat;

    private ChatAdapter adapter;
    public static ChatActivity instance;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initAttributes() {
        instance = this;
    }

    @Override
    protected void initViews() {
        adapter = new ChatAdapter(this);
        rcvChat.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rcvChat.setAdapter(adapter);
    }

    @OnClick(R.id.btn_send)
    public void onSendClick() {
        DXApplication.get(context).sendMessage(Utils.getText(edtMessage), "5");
    }

    @OnClick(R.id.imv_back)
    public void onBackClick() {
        finish();
    }

    public void setMessageResponse(final String message) {
        ChatActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Utils.showToast(context, message);
                adapter.setData(message);
                edtMessage.setText(Constant.EMPTY);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        instance = null;
//        if (channel != null) {
//            Command unsubscribe = Command.unsubscribe(channel.toIdentifier());
//            socket.send(unsubscribe.toJson());
//        }
    }
}

