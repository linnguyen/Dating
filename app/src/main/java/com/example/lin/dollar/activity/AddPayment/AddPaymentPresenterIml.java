package com.example.lin.dollar.activity.AddPayment;

import android.content.Context;

import com.example.lin.dollar.entity.Response.Payment;

/**
 * Created by ryne on 26/09/2017.
 */

public class AddPaymentPresenterIml implements AddPaymentPresenter, AddPaymentPresenter.OnAddPaymentFinishedListener {

    private Context context;
    private AddPaymentView addPaymentView;
    private AddPaymentInteractor addPaymentInteractor;

    public AddPaymentPresenterIml(Context context, AddPaymentView addPaymentView) {
        this.context = context;
        this.addPaymentView = addPaymentView;
        addPaymentInteractor = new AddPaymentInteractorIml(context);
    }

    @Override
    public void addPayment(Context context, Payment payment) {
        addPaymentView.showProgress();
        addPaymentInteractor.addPayment(payment, this);
    }

    @Override
    public void onSuccess(String message) {
        addPaymentView.hideProgress();
        addPaymentView.showMessage(message);
    }

    @Override
    public void onFailure(String message) {
        addPaymentView.hideProgress();
        addPaymentView.showMessage(message);
    }
}
