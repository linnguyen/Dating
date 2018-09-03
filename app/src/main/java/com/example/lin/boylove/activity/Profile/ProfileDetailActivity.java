package com.example.lin.boylove.activity.Profile;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.lin.boylove.DXApplication;
import com.example.lin.boylove.R;
import com.example.lin.boylove.activity.DxBaseActivity;
import com.example.lin.boylove.custom.commons.ImageLoader;
import com.example.lin.boylove.custom.messages.MessagesListAdapter;
import com.example.lin.boylove.entity.Response.ChatMessage;
import com.example.lin.boylove.entity.Response.ChatRoom;
import com.example.lin.boylove.entity.Response.ListChatMessage;
import com.example.lin.boylove.entity.Response.User;
import com.example.lin.boylove.localstorage.SessionManager;
import com.example.lin.boylove.utilities.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class ProfileDetailActivity extends DxBaseActivity implements
        ProfileDetailView {
//    @BindView(R.id.message_list)
//    MessagesList messagesList;

    // private ChatAdapter adapter;
    private ChatRoom chatRoom;
    private User otherUser;

    public static ProfileDetailActivity instance;
    private ProfileDetailPresenter presenter;

    protected int senderId;
    protected ImageLoader imageLoader;
    protected MessagesListAdapter adapter;

    public static void open(Fragment fragment) {
        Intent intent = new Intent(fragment.getActivity(), ProfileDetailActivity.class);
        fragment.startActivity(intent);
    }

    public static void open(Fragment fragment, User other) {
        Intent intent = new Intent(fragment.getActivity(), ProfileDetailActivity.class);
        intent.putExtra(Constant.OTHER_USER, other);
        fragment.startActivity(intent);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_profile_detail;
    }

    @Override
    protected void initAttributes() {
        instance = this;
        presenter = new ProfileDetailPresenterIml(this, context);
        this.senderId = SessionManager.getInstance(context).getUserId();
    }

    @Override
    protected void initViews() {
//        Intent intent = getIntent();
//        if (intent.hasExtra(Constant.CHAT_ROOM)) {
//            chatRoom = intent.getParcelableExtra(Constant.CHAT_ROOM);
//        }
//
//        if (intent.hasExtra(Constant.OTHER_USER)) {
//            otherUser = intent.getParcelableExtra(Constant.OTHER_USER);
//        }
//        adapter = new MessagesListAdapter(senderId, imageLoader);
//        messagesList.setAdapter(adapter);
//        adapter.enableSelectionMode(this);
//        adapter.setLoadMoreListener(this);
//        adapter.setDateHeadersFormatter(this);
//        input.setInputListener(this);
//
//        if (chatRoom != null) {
//            tvName.setText(chatRoom.getTopic());
//            // get chat messages by room id
//            presenter.getMessagesByRoom(chatRoom.getId());
//        }
//
//        if (otherUser != null) {
//            tvName.setText(otherUser.getName());
////            presenter.getMessagesForPrivateRoom(otherUser.getId());
//            presenter.getPrivateRoom(otherUser.getId());
//        }F
    }

//    @OnClick(R.id.imv_back)
//    public void onBackClick() {
//        finish();
//    }

    public void setMessageResponse(final ChatMessage message) {
        // if the room is existed
        if (chatRoom != null && message.getChatroom().getId() == chatRoom.getId()) {
            ProfileDetailActivity.this.runOnUiThread(new Runnable() {
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
                otherUser = null; // no  need to use other user for the next sending message
                ProfileDetailActivity.this.runOnUiThread(new Runnable() {
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
}

