package com.example.lin.boylove.fragment.viewpaper.Income;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.lin.boylove.R;
import com.example.lin.boylove.adapter.IncomeAdapter;
import com.example.lin.boylove.entity.Response.Income;
import com.example.lin.boylove.fragment.DxBaseFragment;
import com.example.lin.boylove.utilities.Utils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by lin on 20/08/2017.
 */

public class IncomeFragment extends DxBaseFragment implements IncomeView {
    @BindView(R.id.rv_income)
    RecyclerView rvIncome;
    @BindView(R.id.pg_loading)
    ProgressBar pgLoading;

    private IncomePresenter incomePresenter;
    private IncomeAdapter incomeAdapter;

    public IncomeFragment() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_income;
    }

    @Override
    protected void initViews() {
        setupListIncome();
    }

    @Override
    protected void initAttributes() {
        incomePresenter = new IncomePresenterIml(mContext, this);
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
