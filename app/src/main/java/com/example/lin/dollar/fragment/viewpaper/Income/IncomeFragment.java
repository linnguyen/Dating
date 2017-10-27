package com.example.lin.dollar.fragment.viewpaper.Income;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.lin.dollar.R;
import com.example.lin.dollar.entity.Response.Income;
import com.example.lin.dollar.fragment.DxBaseFragment;
import com.example.lin.dollar.fragment.adapter.IncomeAdapter;
import com.example.lin.dollar.utilities.Utils;

import java.util.List;

/**
 * Created by lin on 20/08/2017.
 */

public class IncomeFragment extends DxBaseFragment implements IncomeView {
    private RecyclerView rvIncome;
    private ProgressBar pgLoading;

    private IncomePresenter incomePresenter;
    private IncomeAdapter incomeAdapter;

    public IncomeFragment() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_income;
    }

    @Override
    protected void initViews(View view) {
        rvIncome = (RecyclerView) view.findViewById(R.id.rv_income);
        pgLoading = (ProgressBar) view.findViewById(R.id.pg_loading);
        incomePresenter = new IncomePresenterIml(mContext, this);
        setupListIncome();
    }

    private void setupListIncome() {
        rvIncome.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        rvIncome.setLayoutManager(mLayoutManager);
        incomeAdapter = new IncomeAdapter();
        rvIncome.setAdapter(incomeAdapter);
    }

    private void getListIncome() {
        incomePresenter.getListIncome();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && getContext() != null) {
            //check internet connection here
            getListIncome();
        }
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
    public void getListIncomeSuccess(List<Income> listIncome) {
        incomeAdapter.setIncomeData(listIncome);
    }
}
