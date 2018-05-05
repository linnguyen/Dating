package com.example.lin.boylove.fragment.viewpaper.Income;

import com.example.lin.boylove.entity.Response.Income;

import java.util.List;

/**
 * Created by ryne on 27/10/2017.
 */

public interface IncomeView {
    void showProgressBar();

    void hideProgressBar();

    void showMessage(String message);

    void getListIncomeSuccess(List<Income> listIncome);
}
