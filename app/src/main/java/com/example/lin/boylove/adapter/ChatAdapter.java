package com.example.lin.boylove.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lin.boylove.R;
import com.example.lin.boylove.entity.Response.Online;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryne on 13/10/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private Context context;
    private List<String> list;

    private OnlineListener listener;

    public ChatAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String message = list.get(position);
        // Processing for display view here
        holder.tvContent.setText(message);
    }

    @Override
    public int getItemCount() {
        if (null == list) return 0;
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick();
        }
    }

    public void setData(String message) {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        this.list.add(message);
        notifyDataSetChanged();
    }

    public void setListData(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public interface OnlineListener {
        void onClick();
    }
}
