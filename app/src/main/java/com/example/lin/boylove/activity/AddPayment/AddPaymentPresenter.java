package com.example.lin.boylove.activity.AddPayment;

import android.content.Context;

import com.example.lin.boylove.entity.Response.Payment;

/**
 * Created by ryne on 26/09/2017.
 */

public interface AddPaymentPresenter {
    interface OnAddPaymentFinishedListener {
        void onSuccess(String message);

        void onFailure(String message);
    }

    void addPayment(Context context, Payment payment);

}
