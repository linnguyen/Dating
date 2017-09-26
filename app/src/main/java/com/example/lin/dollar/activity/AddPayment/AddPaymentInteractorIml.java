package com.example.lin.dollar.activity.AddPayment;

import android.content.Context;
import android.util.Log;

import com.example.lin.dollar.Entity.Response.Payment;
import com.example.lin.dollar.R;
import com.example.lin.dollar.Service.DolaxAPIs;
import com.example.lin.dollar.localstorage.SessionManager;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ryne on 26/09/2017.
 */

public class AddPaymentInteractorIml implements AddPaymentInteractor {
    private Context context;
    private DolaxAPIs dolaxAPIs;

    public AddPaymentInteractorIml(Context context) {
        this.context = context;
        dolaxAPIs = DolaxAPIs.Factory.create();
    }

    @Override
    public void addPayment(Payment payment, final AddPaymentPresenter.OnAddPaymentFinishedListener listener) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", payment.getName());
        jsonObject.addProperty("fee", payment.getFee());
        Call<Payment> call = dolaxAPIs.createPayment(SessionManager.getInstance(context).getToken(), jsonObject);
        call.enqueue(new Callback<Payment>() {
            @Override
            public void onResponse(Call<Payment> call, Response<Payment> response) {
                Log.d("RESPONSE", response.body().toString());
                if (response.isSuccessful()) {
                    Payment payment = response.body();
                    if (payment != null) {
                        listener.onSuccess(context.getString(R.string.create_success));
                    } else {
                        listener.onFailure(context.getString(R.string.create_fail));
                    }
                } else {
                    listener.onFailure(context.getString(R.string.create_fail));
                }
            }

            @Override
            public void onFailure(Call<Payment> call, Throwable t) {
                Log.d("RYNE", "FAILS");
                listener.onFailure(context.getString(R.string.create_fail));
            }
        });
    }
}
