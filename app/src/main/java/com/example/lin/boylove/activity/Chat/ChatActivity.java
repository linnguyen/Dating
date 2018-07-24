package com.example.lin.boylove.activity.Chat;

import android.content.Intent;
import android.support.v4.app.Fragment;

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
import com.example.lin.boylove.entity.Response.ListChatMessage;
import com.example.lin.boylove.entity.Response.User;
import com.example.lin.boylove.localstorage.SessionManager;
import com.example.lin.boylove.utilities.Constant;
import com.example.lin.boylove.utilities.DateFormatter;
import com.example.lin.boylove.utilities.Utils;

import java.util.Date;

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

    // private ChatAdapter adapter;
    private ChatRoom chatRoom;
    private User otherUser;

    public static ChatActivity instance;
    private ChatPresenter presenter;

    protected int senderId;
    protected ImageLoader imageLoader;
    protected MessagesListAdapter adapter;

    public static void open(Fragment fragment, ChatRoom chatRoom) {
        Intent intent = new Intent(fragment.getActivity(), ChatActivity.class);
        intent.putExtra(Constant.CHAT_ROOM, chatRoom);
        fragment.startActivity(intent);
    }

    public static void open(Fragment fragment, User other) {
        Intent intent = new Intent(fragment.getActivity(), ChatActivity.class);
        intent.putExtra(Constant.OTHER_USER, other);
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

        if (intent.hasExtra(Constant.OTHER_USER)) {
            otherUser = intent.getParcelableExtra(Constant.OTHER_USER);
        }
        adapter = new MessagesListAdapter(senderId, imageLoader);
        messagesList.setAdapter(adapter);
        adapter.enableSelectionMode(this);
        adapter.setLoadMoreListener(this);
        adapter.setDateHeadersFormatter(this);
        input.setInputListener(this);

        if (chatRoom != null) {
            // get chat messages by room id
            presenter.getMessagesByRoom(chatRoom.getId());
        }

        if (otherUser != null) {
//            presenter.getMessagesForPrivateRoom(otherUser.getId());
            presenter.getPrivateRoom(otherUser.getId());
        }
    }

    @OnClick(R.id.imv_back)
    public void onBackClick() {
        finish();
    }

    public void setMessageResponse(final ChatMessage message) {
        // if the room is existed
        if (chatRoom != null && message.getChatroom().getId() == chatRoom.getId()) {
            ChatActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.addToStart(message, true);
//                edtMessage.setText(Constant.EMPTY);
                }
            });
            return;
        }

        // new room created
        if (chatRoom == null && message.getChatroom() != null) {
            if (otherUser.getId() == message.getUser().getId() ||
                    SessionManager.getInstance(context).getUserId() == message.getUser().getId()) {
                chatRoom = message.getChatroom(); // assigned new chat room
                otherUser = null; // no  need to user other user for the next sending message
                ChatActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addToStart(message, true);
//                edtMessage.setText(Constant.EMPTY);
                    }
                });
            }
            return;
        }
    }

    @Override
    public void onGetMessagesSuccess(ListChatMessage messagesRoom) {
        adapter.addToEnd(messagesRoom.getLstMessage(), true);
    }

    @Override
    public void onGetPrivateRoomSucess(ChatRoom room) {
        if (room != null) {
            chatRoom = room;
            presenter.getMessagesByRoom(chatRoom.getId());
        }
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
        if (chatRoom != null) {
            DXApplication.get(context).sendMessage(input.toString(), chatRoom.getId(), -1);
            return true;
        }

        if (otherUser != null) {
            DXApplication.get(context).sendMessage(input.toString(), -1, otherUser.getId());
            return true;
        }
        return true;
    }

    @Override
    public String format(Date date) {
        if (DateFormatter.isToday(date)) {
            return getString(R.string.date_header_today);
        } else if (DateFormatter.isYesterday(date)) {
            return getString(R.string.date_header_yesterday);
        } else {
            return DateFormatter.format(date, DateFormatter.Template.STRING_DAY_MONTH_YEAR);
        }
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

