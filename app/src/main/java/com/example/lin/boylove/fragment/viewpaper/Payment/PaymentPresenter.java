package com.example.lin.boylove.fragment.viewpaper.Payment;

import com.example.lin.boylove.entity.Response.Payment;

import java.util.List;

/**
 * Created by lin on 24/09/2017.
 */

public interface PaymentPresenter {
    interface OnPaymentFinishedListener {
        void onGetPaymentsSuccess(List<Payment> listPayment);

        void onGetPaymentsFail(String message);
    }

    void getListPayment();
}
