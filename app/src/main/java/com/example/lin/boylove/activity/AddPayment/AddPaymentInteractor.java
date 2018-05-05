package com.example.lin.boylove.activity.AddPayment;

import com.example.lin.boylove.entity.Response.Payment;

/**
 * Created by ryne on 26/09/2017.
 */

public interface AddPaymentInteractor {
    void addPayment(Payment payment, AddPaymentPresenter.OnAddPaymentFinishedListener listener);
}
