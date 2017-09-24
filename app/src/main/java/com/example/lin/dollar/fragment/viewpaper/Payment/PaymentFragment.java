package com.example.lin.dollar.fragment.viewpaper.Payment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lin.dollar.R;
import com.example.lin.dollar.entities.Charge;
import com.example.lin.dollar.fragment.adapter.ChargeAdapter;

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
    @BindView(R.id.rvCharge)
    RecyclerView rvCharge;
    private List<Charge> chargeList;
    private ChargeAdapter chargeAdapter;

    private PaymentPresenter paymentPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_charge, container, false);
        ButterKnife.bind(this, view);
//        paymentPresenter = new PaymentPresenterIml(this);
        chargeList = new ArrayList<>();
        chargeAdapter = new ChargeAdapter(chargeList);
        rvCharge.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvCharge.setLayoutManager(mLayoutManager);
//        fadeCharge();
        getListPayment();
        rvCharge.setAdapter(chargeAdapter);
        return view;
    }

    private void getListPayment(){
        paymentPresenter.getListPayment();
    }

    public void fadeCharge() {
        for (int i = 0; i < 5; i++) {
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            Charge charge = new Charge("Action name here", 134000, date);
            chargeList.add(charge);
        }
    }


}
