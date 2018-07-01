package com.example.lin.boylove.fragment.Chat;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.lin.boylove.R;
import com.example.lin.boylove.activity.Chat.ChatActivity;
import com.example.lin.boylove.adapter.ChatRoomAdapter;
import com.example.lin.boylove.entity.Response.ChatMessage;
import com.example.lin.boylove.entity.Response.ChatRoom;
import com.example.lin.boylove.fragment.DxBaseFragment;
import com.example.lin.boylove.utilities.Utils;

import java.util.List;

import butterknife.BindView;


public class ChatRoomFragment extends DxBaseFragment implements
        ChatRoomView, ChatRoomAdapter.ChatRoomListener {
    @BindView(R.id.rcv_chat_room)
    RecyclerView rcvChatRoom;
    @BindView(R.id.pg_loading)
    ProgressBar pgLoading;

    private ChatRoomAdapter adapter;
    public static ChatRoomFragment instance;


    private ChatRoomPresenter presenter;

    public ChatRoomFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_chat;
    }

    @Override
    protected void initViews() {
        setupLstChatRoom();
        getLstChatRoom();
    }

    private void setupLstChatRoom() {
        rcvChatRoom.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new ChatRoomAdapter(mContext);
        adapter.setListener(this);
        rcvChatRoom.setAdapter(adapter);
    }

    private void getLstChatRoom() {
        presenter.getLstChatRoom();
    }

    @Override
    protected void initAttributes() {
        presenter = new ChatRoomPresenterIml(mContext, this);
    }

    public void setMessageResponse(final ChatMessage message) {
        ChatRoomFragment.this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Utils.showToast(mContext, message.getContent());
            }
        });
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {
        if (pgLoading != null)
            pgLoading.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void getLstChatRoomSuccess(List<ChatRoom> lstChatRoom) {
        adapter.setListData(lstChatRoom);
    }

    @Override
    public void onPause() {
        super.onPause();
        instance = null;
    }

    @Override
    public void onClick(ChatRoom chatRoom) {
        ChatActivity.toChatRoomActivity(ChatRoomFragment.this, chatRoom);
    }
}
