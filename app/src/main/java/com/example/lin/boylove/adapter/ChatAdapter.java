package com.example.lin.boylove.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lin.boylove.R;
import com.example.lin.boylove.entity.Response.ChatMessage;
import com.example.lin.boylove.localstorage.SessionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ryne on 13/10/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private Context context;
    private List<ChatMessage> list;
    private RecyclerView.LayoutManager layoutManager;

    private ChatListener listener;

    public ChatAdapter(Context context) {
        this.context = context;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public void setListener(ChatListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_sent, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_received, parent, false);
            return new ReceivedMessageHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChatMessage message = list.get(position);
        // Processing for display view here
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
        }
    }

    @Override
    public int getItemCount() {
        if (null == list) return 0;
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage message = list.get(position);
        int userId = SessionManager.getInstance(context).getUserId();
//        if (message.getUser().getId() == userId) {
//            return VIEW_TYPE_MESSAGE_SENT;
//        } else {
//            return VIEW_TYPE_MESSAGE_RECEIVED;
//        }
        return  1;
    }

    public void setData(ChatMessage message) {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        this.list.add(message);
        notifyDataSetChanged();
        listener.smoothScrollToPosition(getItemCount()-1);
    }

    public void setData(List<ChatMessage> lstChatMessage) {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        this.list = lstChatMessage;
        notifyDataSetChanged();
    }

    public class SentMessageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_time)
        TextView tvTime;


        public SentMessageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(ChatMessage message) {
            tvContent.setText(message.getContent());
        }
    }

    public class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.civ_profile)
        CircleImageView civProfile;


        public ReceivedMessageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(ChatMessage message) {
            tvContent.setText(message.getContent());
        }
    }

    public interface ChatListener {
        void onClick();


        void smoothScrollToPosition(int position);
    }
}
