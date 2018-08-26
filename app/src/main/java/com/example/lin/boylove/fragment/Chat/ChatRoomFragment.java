package com.example.lin.boylove.fragment.Chat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.lin.boylove.R;
import com.example.lin.boylove.activity.Chat.ChatActivity;
import com.example.lin.boylove.entity.Response.ChatMessage;
import com.example.lin.boylove.entity.Response.ChatRoom;
import com.example.lin.boylove.entity.Response.ListChatRoom;
import com.example.lin.boylove.fragment.Chat.adapter.DialogsList;
import com.example.lin.boylove.fragment.Chat.adapter.DialogsListAdapter;
import com.example.lin.boylove.fragment.DxBaseFragment;
import com.example.lin.boylove.utilities.GlideUtils;

import butterknife.BindView;


public class ChatRoomFragment extends DxBaseFragment implements
        ChatRoomView, GlideUtils.ImageLoader,
        DialogsListAdapter.OnDialogClickListener<ChatRoom> {
    @BindView(R.id.list_chat_room)
    DialogsList listChatRoom;
    @BindView(R.id.pg_loading)
    ProgressBar pgLoading;

    private DialogsListAdapter adapter;
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

    }

    private void getLstChatRoom() {
        presenter.getLstChatRoom();
    }

    @Override
    protected void initAttributes() {
        presenter = new ChatRoomPresenterIml(mContext, this);
        adapter = new DialogsListAdapter(this);
        adapter.setOnDialogClickListener(this);
        listChatRoom.setAdapter(adapter);
    }

    public void setMessageResponse(final ChatMessage message) {
        ChatRoomFragment.this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Utils.showToast(mContext, message.getContent());
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
    public void getLstChatRoomSuccess(ListChatRoom lstChatRoom) {
        adapter.setItems(lstChatRoom.getLstChatRoom());
    }

    @Override
    public void onPause() {
        super.onPause();
        instance = null;
    }

    @Override
    public void loadImage(String url, ImageView imv) {
        GlideUtils.loadImage(mContext, url, imv);
    }

    @Override
    public void onDialogClick(ChatRoom room) {
        ChatActivity.open(ChatRoomFragment.this, room);
    }
}
