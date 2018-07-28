package com.example.lin.boylove.fragment.LiveStream;

import com.example.lin.boylove.R;
import com.example.lin.boylove.activity.Chat.ChatActivity;
import com.example.lin.boylove.entity.Response.Online;
import com.example.lin.boylove.entity.Response.User;
import com.example.lin.boylove.fragment.DxBaseFragment;
import com.example.lin.boylove.fragment.Online.adapter.OnlineAdapter;
import com.example.lin.boylove.fragment.Profile.ProfilePresenter;
import com.example.lin.boylove.fragment.Profile.ProfilePresenterIml;
import com.example.lin.boylove.utilities.Utils;

/**
 * Created by lin on 20/08/2017.
 */

public class LiveStreamFragment extends DxBaseFragment {

    public LiveStreamFragment() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_livestream;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initAttributes() {
    }

    @Override
    public void showMessage(String message) {
        Utils.showToast(mContext, message);
    }
}
