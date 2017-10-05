package com.example.lin.dollar.fragment.viewpaper.Payment;

import android.content.Context;
import android.util.Log;

import com.example.lin.dollar.entity.Response.Payment;
import com.example.lin.dollar.R;
import com.example.lin.dollar.service.DolaxAPIs;
import com.example.lin.dollar.localstorage.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lin on 24/09/2017.
 */

public class PaymentInteractorIml implements PaymentInteractor {
    private Context context;
    private DolaxAPIs dolaxAPIs;

    public PaymentInteractorIml(Context context) {
        this.context = context;
        dolaxAPIs = DolaxAPIs.Factory.create();
    }

    @Override
    public void getListPayment(final PaymentPresenter.OnPaymentFinishedListener listener) {
        String authToken = SessionManager.getInstance(context).getToken();
        Call<List<Payment>> call = dolaxAPIs.getPayments(authToken);
        call.enqueue(new Callback<List<Payment>>() {
            @Override
            public void onResponse(Call<List<Payment>> call, Response<List<Payment>> response) {
                Log.d("PAYMENTS", response.body().toString());
                if (response.isSuccessful()) {
                    List<Payment> listPayment = response.body();
                    if (listPayment != null) {
                        listener.onGetPaymentsSuccess(listPayment);
                    } else {
                        listener.onGetPaymentsFail(context.getString(R.string.toast_get_payment_fail));
                    }
                } else {
                    listener.onGetPaymentsFail(context.getString(R.string.toast_get_payment_fail));
                }
            }

            @Override
            public void onFailure(Call<List<Payment>> call, Throwable t) {
                Log.d("FAILS", "PAYMENTS");
                listener.onGetPaymentsFail(context.getString(R.string.toast_get_payment_fail));
            }
        });
    }
}
