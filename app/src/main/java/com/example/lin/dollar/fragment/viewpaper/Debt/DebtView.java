package com.example.lin.dollar.fragment.viewpaper.Debt;

import com.example.lin.dollar.entity.Response.Debt;

import java.util.List;

/**
 * Created by ryne on 13/10/2017.
 */

public interface DebtView {
    void showProgressBar();

    void hideProgressBar();

    void getListDebtSuccess(List<Debt> listDebt);
}
