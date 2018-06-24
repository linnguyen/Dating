package com.example.lin.boylove.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lin.boylove.R;
import com.example.lin.boylove.entity.Response.ChatRoom;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.ViewHolder> {
    private Context context;
    private List<ChatRoom> list;

    private ChatRoomListener listener;

    public ChatRoomAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chatroom, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChatRoom chatRoom = list.get(position);
        // Processing for display view here
        holder.tvTopic.setText(chatRoom.getTopic());
        holder.tvLastUpdate.setText(chatRoom.getUpdated_at());
    }

    @Override
    public int getItemCount() {
        if (null == list) return 0;
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_topic)
        TextView tvTopic;
        @BindView(R.id.tv_last_message)
        TextView tvLastMessage;
        @BindView(R.id.tv_last_update)
        TextView tvLastUpdate;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            ChatRoom chatRoom = list.get(position);
            listener.onClick(chatRoom);
        }
    }

    public void setListData(List<ChatRoom> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setListener(ChatRoomListener listener) {
        this.listener = listener;
    }

    public interface ChatRoomListener {
        void onClick(ChatRoom chatRoom);
    }
}
