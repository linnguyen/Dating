package com.example.lin.dollar.fragment.viewpaper.Payment;

import com.example.lin.dollar.entity.Response.Payment;

import java.util.List;

/**
 * Created by lin on 24/09/2017.
 */

public interface PaymentView {
    void showProgress();

    void hideProgress();

    void getListPaymentSuccess(List<Payment> lisPayment);
}
