package com.example.lin.boylove.activity.Profile.adapter;

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
import com.example.lin.boylove.utilities.GlideUtils;
import com.jsibbold.zoomage.ZoomageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    private Context context;
    private List<User> list;

    public ProfileAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_detail, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = list.get(position);
        holder.tvName.setText(user.getUserName());
        if (user.getImage() != null) {
            Glide.with(context)
                    .load(Constant.Config.URL_IMAGE + user.getImage().getUrl())
                    .placeholder(R.drawable.jlbt_flag)
                    .into(holder.imvAvatar);
        }
        // TODO process here
        holder.tvAge.setText("10");
    }

    @Override
    public int getItemCount() {
        if (null == list) return 0;
        return list.size();
    }

    public void setData(List<User> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imv_avatar)
        ImageView imvAvatar;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_age)
        TextView tvAge;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
