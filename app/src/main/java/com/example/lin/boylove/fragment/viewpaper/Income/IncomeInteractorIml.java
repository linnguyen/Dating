package com.example.lin.boylove.fragment.viewpaper.Income;

import android.content.Context;

import com.example.lin.boylove.R;
import com.example.lin.boylove.entity.Response.Income;
import com.example.lin.boylove.localstorage.SessionManager;
import com.example.lin.boylove.service.DolaxAPIs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ryne on 27/10/2017.
 */

public class IncomeInteractorIml implements IncomeInteractor {
    private Context context;
    private DolaxAPIs dolaxAPIs;

    public IncomeInteractorIml(Context context) {
        this.context = context;
        dolaxAPIs = DolaxAPIs.Factory.create();
    }

    @Override
    public void getListIncome(final IncomePresenter.OnIncomeFinishedListener listener) {
        String authToken = SessionManager.getInstance(context).getToken();
        Call<List<Income>> call = dolaxAPIs.getIncomes(authToken);
        call.enqueue(new Callback<List<Income>>() {
            @Override
            public void onResponse(Call<List<Income>> call, Response<List<Income>> response) {
                if (response.isSuccessful()) {
                    List<Income> listIncome = response.body();
                    if (listIncome != null) {
                        listener.onGetIncomesSuccess(listIncome);
                    } else {
                        listener.onGetIncomesFail(context.getString(R.string.toast_get_income_fail));
                    }
                } else {
                    listener.onGetIncomesFail(context.getString(R.string.toast_get_income_fail));
                }
            }

            @Override
            public void onFailure(Call<List<Income>> call, Throwable t) {
                listener.onGetIncomesFail(context.getString(R.string.toast_get_income_fail));
            }
        });
    }
}
