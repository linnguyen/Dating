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
import com.example.lin.boylove.entity.Response.ListNewFeed;
import com.example.lin.boylove.entity.Response.NewFeed;
import com.example.lin.boylove.utilities.Constant;
import com.example.lin.boylove.utilities.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karsk on 25/04/2018.
 */

public class NewfeedAdapter extends RecyclerView.Adapter<NewfeedAdapter.MyViewHolder> {

    Context context;
    List<NewFeed> lstNewFeed = new ArrayList<>();
    RequestManager glide;

    public NewfeedAdapter(Context context, ArrayList<NewFeed> modelFeedArrayList) {
        this.context = context;
        this.lstNewFeed = modelFeedArrayList;
        glide = Glide.with(context);

    }

    public NewfeedAdapter(Context context) {
        this.context = context;
        glide = Glide.with(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newfeed, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final NewFeed modelFeed = lstNewFeed.get(position);

        holder.tv_name.setText(modelFeed.getDescription());
        holder.tv_time.setText("14h");
//        holder.tv_likes.setText(String.valueOf(modelFeed.getLikes()));
//        holder.tv_comments.setText(modelFeed.getComments() + " comments");
//        holder.tv_status.setText(modelFeed.getStatus());

        glide.load(modelFeed.getUser().getAvatar()).into(holder.imgView_proPic);

        if (modelFeed.getImage() != Constant.EMPTY) {
            holder.imgView_postPic.setVisibility(View.GONE);
        } else {
            holder.imgView_postPic.setVisibility(View.VISIBLE);
            glide.load(modelFeed.getImage()).into(holder.imgView_postPic);
        }
    }

    @Override
    public int getItemCount() {
        return lstNewFeed.size();
    }

    public void setNewFeeds(ListNewFeed lstNewFeed) {
        lstNewFeed = lstNewFeed;
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
