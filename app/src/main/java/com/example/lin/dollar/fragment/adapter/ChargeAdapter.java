package com.example.lin.dollar.fragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lin.dollar.R;
import com.example.lin.dollar.entities.Charge;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lin on 20/08/2017.
 */

public class ChargeAdapter extends RecyclerView.Adapter<ChargeAdapter.ViewHolder> {
    List<Charge> chargeList;

    public ChargeAdapter(List<Charge> chargeList) {
        this.chargeList = chargeList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_charge, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Charge charge = chargeList.get(position);
        holder.tvNameCharge.setText(charge.getName());
        holder.tvCost.setText(String.valueOf(charge.getPrice()));
//        holder.tvDayCharge.setText(String.valueOf(charge.getDate()));
    }

    @Override
    public int getItemCount() {
        return chargeList.size();
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
}
