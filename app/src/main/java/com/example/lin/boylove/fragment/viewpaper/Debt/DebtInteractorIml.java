package com.example.lin.boylove.fragment.viewpaper.Debt;

import android.content.Context;

import com.example.lin.boylove.R;
import com.example.lin.boylove.entity.Response.Debt;
import com.example.lin.boylove.localstorage.SessionManager;
import com.example.lin.boylove.service.DolaxAPIs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ryne on 13/10/2017.
 */

public class DebtInteractorIml implements DebtInteractor {
    private Context context;
    private DolaxAPIs dolaxAPIs;

    public DebtInteractorIml(Context context) {
        this.context = context;
        dolaxAPIs = DolaxAPIs.Factory.create();
    }

    @Override
    public void getListDebt(final DebtPresenter.OnDebtFinishedListener listener) {
        String authToken = SessionManager.getInstance(context).getToken();
        Call<List<Debt>> call = dolaxAPIs.getDebts(authToken);
        call.enqueue(new Callback<List<Debt>>() {
            @Override
            public void onResponse(Call<List<Debt>> call, Response<List<Debt>> response) {
                if (response.isSuccessful()) {
                    List<Debt> listDebt = response.body();
                    if (listDebt != null) {
                        listener.onGetDebtsSuccess(listDebt);
                    } else {
                        listener.onGetDebtsFail(context.getString(R.string.toast_get_debt_fail));
                    }
                } else {
                    listener.onGetDebtsFail(context.getString(R.string.toast_get_debt_fail));
                }
            }

            @Override
            public void onFailure(Call<List<Debt>> call, Throwable t) {
                listener.onGetDebtsFail(context.getString(R.string.toast_get_debt_fail));
            }
        });
    }
}
