package com.example.lin.boylove.fragment.Online;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.lin.boylove.R;
import com.example.lin.boylove.activity.Chat.ChatActivity;
import com.example.lin.boylove.adapter.OnlineAdapter;
import com.example.lin.boylove.entity.Response.Online;
import com.example.lin.boylove.fragment.DxBaseFragment;
import com.example.lin.boylove.utilities.Utils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by lin on 20/08/2017.
 */

public class OnlineFragment extends DxBaseFragment implements OnlineView,
        OnlineAdapter.OnlineListener {
    @BindView(R.id.rcv_online)
    RecyclerView rcvOnline;
    @BindView(R.id.pg_loading)
    ProgressBar pgLoading;

    private OnlinePresenter presenter;
    private OnlineAdapter adapter;

    public OnlineFragment() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_online;
    }

    @Override
    protected void initViews() {
        setupLstOnline();
        getLstOnline();
    }

    @Override
    protected void initAttributes() {
        presenter = new OnlinePresenterIml(mContext, this);
    }

    private void setupLstOnline() {
        rcvOnline.setHasFixedSize(true);
        rcvOnline.setLayoutManager(new GridLayoutManager(mContext, 3));
        adapter = new OnlineAdapter(getContext());
        adapter.setListener(this);
        rcvOnline.setAdapter(adapter);
    }

    private void getLstOnline() {
        presenter.getLstOnline();
    }

    @Override
    public void showProgressBar() {
        pgLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        if (pgLoading != null)
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
