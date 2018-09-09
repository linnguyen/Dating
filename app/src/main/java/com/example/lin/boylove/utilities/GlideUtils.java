package com.example.lin.boylove.utilities;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lin.boylove.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lin on 10/07/2018.
 */

public class GlideUtils {
    public static void loadImage(Context context, String url, ImageView imv) {
        Glide.with(context)
                .load(Constant.Config.URL_IMAGE + url)
                .centerCrop()
                .placeholder(R.drawable.jlbt_flag)
                .into(imv);
    }

    // this method for hdodenhof circle image view
    public static void loadImageAvatar(Context context, String url, CircleImageView civ) {
        Glide.with(context)
                .load(Constant.Config.URL_IMAGE + url)
                .centerCrop()
                .dontAnimate()
                .placeholder(R.drawable.ic_profile_avatar)
                .into(civ);
    }

    public interface ImageLoader {
        void loadImage(String url, ImageView imv);
    }
}
