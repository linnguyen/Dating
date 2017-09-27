package com.example.lin.dollar.fragment.viewpaper.Payment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lin.dollar.Entity.Response.User;
import com.example.lin.dollar.R;
import com.example.lin.dollar.Entity.Response.Payment;
import com.example.lin.dollar.Utilities.Utils;
import com.example.lin.dollar.fragment.adapter.PaymentAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lin on 20/08/2017.
 */

public class PaymentFragment extends Fragment implements PaymentView {
    private Context context;
    RecyclerView rvPayment;
    private ProgressDialog progressDialog;

    private PaymentAdapter paymentAdapter;
    private PaymentPresenter paymentPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_charge, container, false);
        context = getContext();
        rvPayment = (RecyclerView) view.findViewById(R.id.rvCharge);
        paymentPresenter = new PaymentPresenterIml(context, this);
        setupListPayment();
//        fakeCharge();
        // check internet here
        getListPayment();
        return view;
    }

    public void getListPayment() {
        paymentPresenter.getListPayment();
    }

    private void setupListPayment() {
        rvPayment.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvPayment.setLayoutManager(mLayoutManager);
        paymentAdapter = new PaymentAdapter();
        rvPayment.setAdapter(paymentAdapter);
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(context, R.style.CustomProgressDialog);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.dialog_loading_payment));
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
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
}
