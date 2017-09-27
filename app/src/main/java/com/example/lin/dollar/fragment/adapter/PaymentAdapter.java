package com.example.lin.dollar.fragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lin.dollar.R;
import com.example.lin.dollar.Entity.Response.Payment;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lin on 20/08/2017.
 */

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolder> {
    List<Payment> listPayment;

    public PaymentAdapter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_charge, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Payment payment = listPayment.get(position);
        holder.tvNameCharge.setText(payment.getName());
        holder.tvCost.setText(Double.toString(payment.getFee()));
        // convert date to string with date format
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm dd/MM/yy");
        String datePayment = simpleDateFormat.format(payment.getDate());
        holder.tvDayCharge.setText(datePayment);
    }

    @Override
    public int getItemCount() {
        if (null == listPayment) return 0;
        return listPayment.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvNameCharge)
        TextView tvNameCharge;
        @BindView(R.id.tvCost)
        TextView tvCost;
        @BindView(R.id.tvDayCharge)
        TextView tvDayCharge;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setPaymentData(List<Payment> listPayment) {
        this.listPayment = listPayment;
        notifyDataSetChanged();
    }
}
