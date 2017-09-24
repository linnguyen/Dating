package com.example.lin.dollar.fragment.viewpaper.Payment;

/**
 * Created by lin on 24/09/2017.
 */

public class PaymentPresenterIml implements PaymentPresenter {
    private PaymentView paymentView;
    private PaymentInteractor paymentInteractor;

    public PaymentPresenterIml(PaymentView paymentView) {
        this.paymentView = paymentView;
        paymentInteractor = new PaymentInteractorIml();
    }

    @Override
    public void getListPayment() {
       paymentInteractor.getListPayment();
    }
}
