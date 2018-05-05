package com.example.lin.boylove.fragment.viewpaper.Income;

import com.example.lin.boylove.entity.Response.Income;

import java.util.List;

/**
 * Created by ryne on 27/10/2017.
 */

public interface IncomePresenter {
    interface OnIncomeFinishedListener {
        void onGetIncomesSuccess(List<Income> listIncome);

        void onGetIncomesFail(String message);
    }

    void getListIncome();
}
