package com.example.lin.boylove.fragment.viewpaper.Debt;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.lin.boylove.R;
import com.example.lin.boylove.entity.Response.Debt;
import com.example.lin.boylove.fragment.DxBaseFragment;
import com.example.lin.boylove.adapter.DebtAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DebtFragment extends DxBaseFragment implements DebtView {
    private RecyclerView rvDebt;
    private ProgressBar pgLoading;

    private DebtAdapter mDebtAdapter;
    private DebtPresenter debtPresenter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_debt;
    }

    @Override
    protected void initViews(View view) {
        rvDebt = (RecyclerView) view.findViewById(R.id.rv_debt);
        pgLoading = (ProgressBar) view.findViewById(R.id.pg_loading);
        debtPresenter = new DebtPresenterIml(mContext, this);
        setupListDebt();
//       fakeDebt();
    }

    private void setupListDebt() {
        rvDebt.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        rvDebt.setLayoutManager(mLayoutManager);
        mDebtAdapter = new DebtAdapter();
        rvDebt.setAdapter(mDebtAdapter);
    }

    private void fakeDebt() {
        List<Debt> debtList = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < 100; i++) {
            Debt debt = new Debt("whold", 100000, c.getTime(), 1, 100);
            debtList.add(debt);
        }
        mDebtAdapter.setDebtData(debtList);
    }

    private void getListDebt() {
        debtPresenter.getListDebt();
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
    public void getListDebtSuccess(List<Debt> listDebt) {
        mDebtAdapter.setDebtData(listDebt);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && mContext != null) {
            // Load data here
//            Utils.showToast(mContext, "Visible");
            // check internet connection here
            getListDebt();
        }
    }
}
