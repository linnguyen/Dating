package com.example.lin.boylove.fragment.NewFeed;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.lin.boylove.R;
import com.example.lin.boylove.entity.Response.ListNewFeed;
import com.example.lin.boylove.fragment.DxBaseFragment;
import com.example.lin.boylove.fragment.NewFeed.adapter.NewfeedAdapter;
import com.example.lin.boylove.utilities.Utils;

import butterknife.BindView;

/**
 * Created by lin on 20/08/2017.
 */

public class NewfeedFragment extends DxBaseFragment implements NewfeedView {
    @BindView(R.id.rcv_newfeed)
    RecyclerView rcvNewFeed;
    @BindView(R.id.pg_loading)
    ProgressBar pgLoading;

    private NewfeedPresenter presenter;
    private NewfeedAdapter adapter;

    public NewfeedFragment() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_newfeed;
    }

    @Override
    protected void initViews() {
        setupLstNewfeeds();
        getNewFeeds();
    }

    private void setupLstNewfeeds() {
        rcvNewFeed.setHasFixedSize(true);
        rcvNewFeed.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new NewfeedAdapter(mContext);
//        adapter = new NewfeedAdapter(mContext, modelFeedArrayList);
//        adapter.setListener(this);
        rcvNewFeed.setAdapter(adapter);
    }

    @Override
    protected void initAttributes() {
        presenter = new NewfeedPresenterIml(mContext, this);
    }

    @Override
    public void showProgressBar() {
        pgLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        pgLoading.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Utils.showToast(mContext, message);
    }

    @Override
    public void onGetNewFeedsSuccess(ListNewFeed lstNewFeed) {
        adapter.setNewFeeds(lstNewFeed.getLstNewFeed());
    }

    private void getNewFeeds() {
        presenter.getNewFeeds();
    }

//    public void populateRecyclerView() {
//
//        NewFeed modelFeed = new NewFeed(1, 9, 2, R.drawable.ic_profile_avatar, R.drawable.jlbt_flag,
//                "Sajin Maharjan", "2 hrs", "The cars we drive say a lot about us.");
//        modelFeedArrayList.add(modelFeed);
//        modelFeed = new NewFeed(2, 26, 6, R.drawable.ic_profile_avatar, 0,
//                "Karun Shrestha", "9 hrs", "Don't be afraid of your fears. They're not there to scare you. They're there to \n" +
//                "let you know that something is worth it.");
//        modelFeedArrayList.add(modelFeed);
//        modelFeed = new NewFeed(3, 17, 5, R.drawable.ic_profile_avatar, R.drawable.jlbt_flag,
//                "Lakshya Ram", "13 hrs", "That reflection!!!");
//        modelFeedArrayList.add(modelFeed);
//
//        adapter.notifyDataSetChanged();
//    }
}
