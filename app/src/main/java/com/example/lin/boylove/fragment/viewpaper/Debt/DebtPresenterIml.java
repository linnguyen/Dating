package com.example.lin.boylove.fragment.viewpaper.Debt;

import android.content.Context;

import com.example.lin.boylove.entity.Response.Debt;

import java.util.List;

/**
 * Created by ryne on 13/10/2017.
 */

public class DebtPresenterIml implements DebtPresenter, DebtPresenter.OnDebtFinishedListener {
    private Context context;
    private DebtView debtView;
    private DebtInteractor debtInteractor;

    public DebtPresenterIml(Context context, DebtView debtView) {
        this.context = context;
        this.debtView = debtView;
        debtInteractor = new DebtInteractorIml(context);
    }

    @Override
    public void getListDebt() {
        debtView.showProgressBar();
        debtInteractor.getListDebt(this);
    }

    @Override
    public void onGetDebtsSuccess(List<Debt> listDebt) {
        debtView.hideProgressBar();
        debtView.getListDebtSuccess(listDebt);
    }

    @Override
    public void onGetDebtsFail(String message) {
        debtView.hideProgressBar();
    }

}
