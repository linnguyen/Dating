package com.example.lin.boylove.fragment.viewpaper.Debt;

import com.example.lin.boylove.entity.Response.Debt;

import java.util.List;

/**
 * Created by ryne on 13/10/2017.
 */

public interface DebtPresenter {
    interface OnDebtFinishedListener {
        void onGetDebtsSuccess(List<Debt> listDebt);

        void onGetDebtsFail(String message);
    }

    void getListDebt();
}
