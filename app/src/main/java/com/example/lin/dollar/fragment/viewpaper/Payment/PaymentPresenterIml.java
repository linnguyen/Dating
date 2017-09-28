package com.example.lin.dollar.fragment.viewpaper.Payment;

import android.content.Context;

import com.example.lin.dollar.entity.Response.Payment;

import java.util.List;

/**
 * Created by lin on 24/09/2017.
 */

public class PaymentPresenterIml implements PaymentPresenter, PaymentPresenter.OnPaymentFinishedListener {
    private Context context;
    private PaymentView paymentView;
    private PaymentInteractor paymentInteractor;

    public PaymentPresenterIml(Context context, PaymentView paymentView) {
        this.context = context;
        this.paymentView = paymentView;
        paymentInteractor = new PaymentInteractorIml(context);
    }

    @Override
    public void getListPayment() {
        paymentView.showProgress();
        paymentInteractor.getListPayment(this);
    }

    @Override
    public void onGetPaymentsSuccess(List<Payment> listPayment) {
        paymentView.hideProgress();
        paymentView.getListPaymentSuccess(listPayment);
    }

    @Override
    public void onGetPaymentsFail(String message) {

    }
}
