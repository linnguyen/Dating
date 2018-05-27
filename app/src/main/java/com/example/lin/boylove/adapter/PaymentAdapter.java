package com.example.lin.boylove.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lin.boylove.R;
import com.example.lin.boylove.utilities.Utils;
import com.example.lin.boylove.entity.Response.Payment;

import java.util.List;

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
                .inflate(R.layout.item_payment, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Payment payment = listPayment.get(position);
        holder.tvNamePayment.setText(payment.getName());
        holder.tvFee.setText(Double.toString(payment.getFee()));
        // convert date to string with date format
        holder.tvDayCharge.setText(Utils.fromDateToDateString("hh:mm dd/MM/yy", payment.getDate()));
    }

    @Override
    public int getItemCount() {
        if (null == listPayment) return 0;
        return listPayment.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamePayment;
        TextView tvFee;
        TextView tvDayCharge;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvNamePayment = (TextView) itemView.findViewById(R.id.tv_name_payment);
            this.tvFee = (TextView) itemView.findViewById(R.id.tv_fee);
            this.tvDayCharge = (TextView) itemView.findViewById(R.id.tv_day_charge);
        }
    }

    public void setPaymentData(List<Payment> listPayment) {
        this.listPayment = listPayment;
        notifyDataSetChanged();
    }
}
