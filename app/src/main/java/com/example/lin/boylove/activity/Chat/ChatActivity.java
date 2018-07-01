package com.example.lin.boylove.activity.Chat;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;

import com.example.lin.boylove.DXApplication;
import com.example.lin.boylove.R;
import com.example.lin.boylove.activity.DxBaseActivity;
import com.example.lin.boylove.adapter.ChatAdapter;
import com.example.lin.boylove.custom.commons.ImageLoader;
import com.example.lin.boylove.custom.messages.MessageInput;
import com.example.lin.boylove.custom.messages.MessagesList;
import com.example.lin.boylove.custom.messages.MessagesListAdapter;
import com.example.lin.boylove.entity.Response.ChatMessage;
import com.example.lin.boylove.entity.Response.ChatRoom;
import com.example.lin.boylove.localstorage.SessionManager;
import com.example.lin.boylove.utilities.Constant;
import com.example.lin.boylove.utilities.DateFormatter;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ChatActivity extends DxBaseActivity implements
        ChatView, ChatAdapter.ChatListener, MessageInput.InputListener,
        MessageInput.AttachmentsListener, MessagesListAdapter.SelectionListener,
        MessagesListAdapter.OnLoadMoreListener,
        DateFormatter.Formatter {
    @BindView(R.id.message_list)
    MessagesList messagesList;
    @BindView(R.id.input)
    MessageInput input;

    //    private ChatAdapter adapter;
    private ChatRoom chatRoom;

    public static ChatActivity instance;
    private ChatPresenter presenter;

    private LinearLayoutManager linearLayoutManager;
    protected int senderId;
    protected ImageLoader imageLoader;
    protected MessagesListAdapter adapter;

    public static void toChatRoomActivity(Fragment fragment, ChatRoom chatRoom) {
        Intent intent = new Intent(fragment.getActivity(), ChatActivity.class);
        intent.putExtra(Constant.CHAT_ROOM, chatRoom);
        fragment.startActivity(intent);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initAttributes() {
        instance = this;
        presenter = new ChatPresenterIml(this, context);
        this.senderId = SessionManager.getInstance(context).getUserId();
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constant.CHAT_ROOM)) {
            chatRoom = intent.getParcelableExtra(Constant.CHAT_ROOM);
        }
//        adapter = new ChatAdapter(this);
//        adapter.setListener(this);
//        linearLayoutManager = new LinearLayoutManager(context,
//                LinearLayoutManager.VERTICAL, false);
//        rcvChat.setLayoutManager(linearLayoutManager);
//        rcvChat.setAdapter(adapter);
        adapter = new MessagesListAdapter(senderId, imageLoader);
        messagesList.setAdapter(adapter);
        adapter.enableSelectionMode(this);
        adapter.setLoadMoreListener(this);
        adapter.setDateHeadersFormatter(this);
        input.setInputListener(this);

        // get chat messages by room id
        presenter.getMessagesByRoom(chatRoom.getId());
    }

    @OnClick(R.id.imv_back)
    public void onBackClick() {
        finish();
    }

    public void setMessageResponse(final ChatMessage message) {
        ChatActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.addToStart(message, true);
//                edtMessage.setText(Constant.EMPTY);
            }
        });
    }

    @Override
    public void onGetMessagesSuccess(List<ChatMessage> lstChatMessage) {
//        adapter.setData(lstChatMessage);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onClick() {

    }

    @Override
    public void smoothScrollToPosition(int position) {

    }

    @Override
    public boolean onSubmit(CharSequence input) {
        DXApplication.get(context).sendMessage(input.toString(), chatRoom.getId());
        return false;
    }

    @Override
    public String format(Date date) {
        return null;
    }

    @Override
    public void onAddAttachments() {

    }

    @Override
    public void onLoadMore(int page, int totalItemsCount) {

    }

    @Override
    public void onSelectionChanged(int count) {

    }
}

