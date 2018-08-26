package com.example.lin.boylove.fragment.NewFeed.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lin.boylove.R;
import com.example.lin.boylove.entity.Response.NewFeed;
import com.example.lin.boylove.utilities.Constant;
import com.example.lin.boylove.utilities.DateFormatter;
import com.example.lin.boylove.utilities.GlideUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by karsk on 25/04/2018.
 */

public class NewfeedAdapter extends RecyclerView.Adapter<NewfeedAdapter.MyViewHolder> {

    Context context;
    List<NewFeed> lstNewFeed;

    public NewfeedAdapter(Context context, ArrayList<NewFeed> modelFeedArrayList) {
        this.context = context;
        this.lstNewFeed = modelFeedArrayList;
    }

    public NewfeedAdapter(Context context) {
        this.context = context;
        lstNewFeed = new ArrayList<>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newfeed, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final NewFeed feed = lstNewFeed.get(position);
        holder.tvName.setText(feed.getUser().getEmail());
        holder.tvTime.setText(DateFormatter.getTimeAgo(context, feed.getCreated_at()));
        holder.tvStatus.setText(feed.getDescription());
//        holder.tv_likes.setText(String.valueOf(modelFeed.getLikes()));
//        holder.tv_comments.setText(modelFeed.getComments() + " comments");
//        holder.tv_status.setText(modelFeed.getStatus());
        GlideUtils.loadImageAvatar(context, feed.getUser().getImage().getUrl(), holder.imvUser);

        if (feed.getImage().getUrl() != Constant.EMPTY) {
            holder.imvPost.setVisibility(View.VISIBLE);
            GlideUtils.loadImage(context, feed.getImage().getUrl(), holder.imvPost);
        } else {
            holder.imvPost.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return lstNewFeed.size();
    }

    public void setNewFeeds(List<NewFeed> lstNewFeed) {
        this.lstNewFeed = lstNewFeed;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imv_user)
        CircleImageView imvUser;
        @BindView(R.id.imv_post)
        ImageView imvPost;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_like)
        TextView tvLike;
        @BindView(R.id.tv_comment)
        TextView tvComment;
        @BindView(R.id.tv_status)
        TextView tvStatus;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
