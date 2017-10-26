package com.example.lin.dollar.fragment.viewpaper.Payment;

import android.app.ProgressDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.lin.dollar.R;
import com.example.lin.dollar.entity.Response.Payment;
import com.example.lin.dollar.fragment.DxBaseFragment;
import com.example.lin.dollar.fragment.adapter.PaymentAdapter;
import com.example.lin.dollar.utilities.Utils;

import java.util.List;

/**
 * Created by lin on 20/08/2017.
 */

public class PaymentFragment extends DxBaseFragment implements PaymentView {
    private RecyclerView rvPayment;
    private ProgressBar pgLoading;

    private PaymentAdapter paymentAdapter;
    private PaymentPresenter paymentPresenter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_payment;
    }

    @Override
    protected void initViews(View view) {
        rvPayment = (RecyclerView) view.findViewById(R.id.rvCharge);
        pgLoading = (ProgressBar) view.findViewById(R.id.pg_loading);
        paymentPresenter = new PaymentPresenterIml(mContext, this);
        setupListPayment();
        // check internet here
        getListPayment();
    }

    public void getListPayment() {
        paymentPresenter.getListPayment();
    }

    private void setupListPayment() {
        rvPayment.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        rvPayment.setLayoutManager(mLayoutManager);
        paymentAdapter = new PaymentAdapter();
        rvPayment.setAdapter(paymentAdapter);
    }

    @Override
    public void showProgress() {
//        progressDialog = new ProgressDialog(mContext, R.style.CustomProgressDialog);
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDialog.setCanceledOnTouchOutside(false);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage(getString(R.string.dialog_loading_payment));
//        progressDialog.show();
        pgLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        if (pgLoading != null) {
            pgLoading.setVisibility(View.GONE);
        }
    }

    @Override
    public void getListPaymentSuccess(List<Payment> lisPayment) {
        paymentAdapter.setPaymentData(lisPayment);
    }

    @Override
    public void onResume() {
        paymentAdapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && mContext != null) {
            // Load data here
            Utils.showToast(mContext, "Visible");
        }
    }
}
