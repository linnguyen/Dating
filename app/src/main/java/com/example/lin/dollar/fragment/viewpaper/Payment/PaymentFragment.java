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

    private List<Payment> listPayment;
    private PaymentAdapter chargeAdapter;
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
        listPayment = new ArrayList<>();
//        fakeCharge();
        // check internet here
        getListPayment();
        return view;
    }

    private void getListPayment() {
        paymentPresenter.getListPayment();
    }

    private void setupListPayment() {
        chargeAdapter = new PaymentAdapter(listPayment);
        rvPayment.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvPayment.setLayoutManager(mLayoutManager);
        rvPayment.setAdapter(chargeAdapter);
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
        this.listPayment.clear();
        this.listPayment.addAll(lisPayment);
        setupListPayment();
    }

    @OnClick(R.id.imv_close_add_payment)
    public void closeFormAddPayment() {

    }

    public void fakeCharge() {
        for (int i = 0; i < 5; i++) {
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            Payment charge = new Payment("Action name here", 134000, date);
            listPayment.add(charge);
        }
    }

}
