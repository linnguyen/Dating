package com.example.lin.dollar.fragment.viewpaper.Income;

import android.content.Context;

import com.example.lin.dollar.entity.Response.Income;

import java.util.List;

/**
 * Created by ryne on 27/10/2017.
 */

public class IncomePresenterIml implements IncomePresenter, IncomePresenter.OnIncomeFinishedListener {
    private Context context;
    private IncomeView incomeView;
    private IncomeInteractor incomeInteractor;

    public IncomePresenterIml(Context context, IncomeView incomeView) {
        this.context = context;
        this.incomeView = incomeView;
        incomeInteractor = new IncomeInteractorIml(context);
    }

    @Override
    public void onGetIncomesSuccess(List<Income> listIncome) {
        incomeView.hideProgressBar();
        incomeView.getListIncomeSuccess(listIncome);
    }

    @Override
    public void onGetIncomesFail(String message) {
        incomeView.hideProgressBar();
        incomeView.showMessage(message);
    }

    @Override
    public void getListIncome() {
        incomeView.showProgressBar();
        incomeInteractor.getListIncome(this);
    }
}
