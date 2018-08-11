package com.example.lin.boylove.fragment.NewFeed.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.lin.boylove.R;
import com.example.lin.boylove.entity.Response.NewFeed;
import com.example.lin.boylove.utilities.Constant;
import com.example.lin.boylove.utilities.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karsk on 25/04/2018.
 */

public class NewfeedAdapter extends RecyclerView.Adapter<NewfeedAdapter.MyViewHolder> {

    Context context;
    List<NewFeed> lstNewFeed;
    RequestManager glide;

    public NewfeedAdapter(Context context, ArrayList<NewFeed> modelFeedArrayList) {
        this.context = context;
        this.lstNewFeed = modelFeedArrayList;
        glide = Glide.with(context);

    }

    public NewfeedAdapter(Context context) {
        this.context = context;
        glide = Glide.with(context);
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
        holder.tv_name.setText(feed.getUser().getEmail());
        holder.tv_time.setText(feed.getCreated_at());
        holder.tv_status.setText(feed.getDescription());
//        holder.tv_likes.setText(String.valueOf(modelFeed.getLikes()));
//        holder.tv_comments.setText(modelFeed.getComments() + " comments");
//        holder.tv_status.setText(modelFeed.getStatus());

        glide.load(feed.getUser().getAvatar()).into(holder.imgView_proPic);

        if (feed.getImage().getUrl() != Constant.EMPTY) {
            holder.imgView_postPic.setVisibility(View.VISIBLE);
            GlideUtils.loadImage(context, feed.getImage().getUrl(), holder.imgView_postPic);
        } else {
            holder.imgView_postPic.setVisibility(View.GONE);
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

        TextView tv_name, tv_time, tv_likes, tv_comments, tv_status;
        ImageView imgView_proPic, imgView_postPic;

        public MyViewHolder(View itemView) {
            super(itemView);

            imgView_proPic = (ImageView) itemView.findViewById(R.id.imgView_proPic);
            imgView_postPic = (ImageView) itemView.findViewById(R.id.imgView_postPic);

            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_likes = (TextView) itemView.findViewById(R.id.tv_like);
            tv_comments = (TextView) itemView.findViewById(R.id.tv_comment);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
        }
    }
}
