package com.example.lin.dollar.fragment.viewpaper.Payment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lin.dollar.R;
import com.example.lin.dollar.Entity.Response.Payment;
import com.example.lin.dollar.fragment.adapter.PaymentAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lin on 20/08/2017.
 */

public class PaymentFragment extends Fragment implements PaymentView {
    private Context context;
    @BindView(R.id.rvCharge)
    RecyclerView rvCharge;
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
        ButterKnife.bind(this, view);
        context = getContext();
        paymentPresenter = new PaymentPresenterIml(context, this);
        listPayment = new ArrayList<>();
//        fadeCharge();
        // check internet here
        getListPayment();
        return view;
    }

    private void getListPayment() {
        paymentPresenter.getListPayment();
    }

    private void setupListPayment() {
        chargeAdapter = new PaymentAdapter(listPayment);
        rvCharge.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvCharge.setLayoutManager(mLayoutManager);
        rvCharge.setAdapter(chargeAdapter);
    }

    @Override
    public void getListPaymentSuccess(List<Payment> lisPayment) {
        this.listPayment.clear();
        this.listPayment.addAll(lisPayment);
        setupListPayment();
    }

    public void fadeCharge() {
        for (int i = 0; i < 5; i++) {
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            Payment charge = new Payment("Action name here", 134000, date);
            listPayment.add(charge);
        }
    }
}
