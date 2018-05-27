package com.example.lin.boylove.fragment.Online;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.lin.boylove.R;
import com.example.lin.boylove.activity.Chat.ChatActivity;
import com.example.lin.boylove.entity.Response.Online;
import com.example.lin.boylove.fragment.DxBaseFragment;
import com.example.lin.boylove.adapter.OnlineAdapter;
import com.example.lin.boylove.utilities.Utils;

import java.util.List;

/**
 * Created by lin on 20/08/2017.
 */

public class OnlineFragment extends DxBaseFragment implements OnlineView,
        OnlineAdapter.OnlineListener {
    private RecyclerView rvIncome;
    private ProgressBar pgLoading;

    private OnlinePresenter presenter;
    private OnlineAdapter adapter;

    public OnlineFragment() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_income;
    }

    @Override
    protected void initViews(View view) {
        rvIncome = (RecyclerView) view.findViewById(R.id.rv_income);
        pgLoading = (ProgressBar) view.findViewById(R.id.pg_loading);
        presenter = new OnlinePresenterIml(mContext, this);
        setupListIncome();
        getListIncome();
    }

    private void setupListIncome() {
        rvIncome.setHasFixedSize(true);
        rvIncome.setLayoutManager(new GridLayoutManager(mContext, 3));
        adapter = new OnlineAdapter(getContext());
        adapter.setListener(this);
        rvIncome.setAdapter(adapter);
    }

    private void getListIncome() {
        presenter.getListIncome();
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
    public void getListOnlineSuccess(List<Online> lstOnline) {
        adapter.setData(lstOnline);
    }

    @Override
    public void onClick() {
        Intent intent = new Intent(this.getActivity(), ChatActivity.class);
        startActivity(intent);
    }
}
