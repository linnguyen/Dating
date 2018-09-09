package com.example.lin.boylove.fragment.Online.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lin.boylove.R;
import com.example.lin.boylove.entity.Response.User;
import com.example.lin.boylove.utilities.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ryne on 13/10/2017.
 */

public class OnlineAdapter extends RecyclerView.Adapter<OnlineAdapter.ViewHolder> {
    private Context context;
    private List<User> list;

    private OnlineListener listener;

    public OnlineAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_online, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = list.get(position);
        holder.tvStatus.setVisibility(user.isOnline() ? View.VISIBLE : View.GONE);
        // Processing for display view here
        String imageUrl = Constant.Config.URL_IMAGE + user.getImage().getUrl();
        Glide.with(context)
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.jlbt_flag)
                .into(holder.imvUser);
    }

    @Override
    public int getItemCount() {
        if (null == list) return 0;
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.imv_user)
        ImageView imvUser;

        @BindView(R.id.tv_status)
        TextView tvStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(list);
        }
    }

    public void setData(List<User> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setListener(OnlineListener listener) {
        this.listener = listener;
    }

    public interface OnlineListener {
        // should not pass list of user here, cause if the list is bigger, may cause crash when pass with the bundle
        void onClick(List<User> lstUser);
    }
}
