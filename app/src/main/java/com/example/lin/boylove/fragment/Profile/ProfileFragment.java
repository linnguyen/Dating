package com.example.lin.boylove.fragment.Profile;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.lin.boylove.R;
import com.example.lin.boylove.activity.Chat.ChatActivity;
import com.example.lin.boylove.entity.Response.Online;
import com.example.lin.boylove.entity.Response.User;
import com.example.lin.boylove.fragment.DxBaseFragment;
import com.example.lin.boylove.fragment.Online.adapter.OnlineAdapter;
import com.example.lin.boylove.utilities.Utils;

import butterknife.BindView;

/**
 * Created by lin on 20/08/2017.
 */

public class ProfileFragment extends DxBaseFragment implements ProfileView,
        OnlineAdapter.OnlineListener {
//    @BindView(R.id.rcv_online)
//    RecyclerView rcvOnline;
//    @BindView(R.id.pg_loading)
//    ProgressBar pgLoading;

    private ProfilePresenter presenter;
    private OnlineAdapter adapter;

    public ProfileFragment() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void initViews() {
        setupLstOnline();
        getLstOnline();
    }

    @Override
    protected void initAttributes() {
        presenter = new ProfilePresenterIml(mContext, this);
    }

    private void setupLstOnline() {

    }

    private void getLstOnline() {
        presenter.getLstOnline();
    }

    @Override
    public void showProgressBar() {
    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showMessage(String message) {
        Utils.showToast(mContext, message);
    }

    @Override
    public void getListOnlineSuccess(Online online) {

    }

    @Override
    public void onClick(User other) {
        ChatActivity.open(ProfileFragment.this, other);
    }
}
