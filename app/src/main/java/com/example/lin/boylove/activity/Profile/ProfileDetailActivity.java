package com.example.lin.boylove.activity.Profile;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

import com.example.lin.boylove.R;
import com.example.lin.boylove.activity.DxBaseActivity;
import com.example.lin.boylove.activity.Profile.adapter.ProfileAdapter;
import com.example.lin.boylove.custom.commons.ImageLoader;
import com.example.lin.boylove.entity.Response.User;
import com.example.lin.boylove.localstorage.SessionManager;
import com.example.lin.boylove.utilities.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ProfileDetailActivity extends DxBaseActivity
        implements ProfileDetailView {
    @BindView(R.id.rcv_user_detail)
    RecyclerView rcvUserDetail;

    private List<User> lstUser;
    private ProfileDetailPresenter presenter;

    protected int senderId;
    protected ImageLoader imageLoader;
    protected ProfileAdapter adapter;

    public static void open(Fragment fragment, List<User> users) {
        Intent intent = new Intent(fragment.getActivity(), ProfileDetailActivity.class);
        intent.putParcelableArrayListExtra(Constant.LIST_USER, (ArrayList<? extends Parcelable>) users);
        fragment.startActivity(intent);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_profile_detail;
    }

    @Override
    protected void initAttributes() {
        presenter = new ProfileDetailPresenterIml(this, context);
        this.senderId = SessionManager.getInstance(context).getUserId();
    }

    @Override
    protected void initViews() {
        setupLstProfileAvatar();
        Intent intent = getIntent();
        if (intent.hasExtra(Constant.LIST_USER)) {
            lstUser = intent.getParcelableArrayListExtra(Constant.LIST_USER);
            adapter.setData(lstUser);
        }
    }

    private void setupLstProfileAvatar() {
        rcvUserDetail.setHasFixedSize(true);
        rcvUserDetail.setLayoutManager(new LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false));
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rcvUserDetail);
        adapter = new ProfileAdapter(context);
        rcvUserDetail.setAdapter(adapter);
    }

    @OnClick(R.id.imv_chat)
    public void onBackClick() {

    }

}

