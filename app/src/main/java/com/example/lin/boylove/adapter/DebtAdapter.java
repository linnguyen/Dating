package com.example.lin.boylove.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lin.boylove.R;
import com.example.lin.boylove.entity.Response.Debt;
import com.example.lin.boylove.utilities.Utils;

import java.util.List;

/**
 * Created by ryne on 13/10/2017.
 */

public class DebtAdapter extends RecyclerView.Adapter<DebtAdapter.ViewHolder> {
    private List<Debt> mListDebt;

    public DebtAdapter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_debt, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Debt debt = mListDebt.get(position);
        // Processing for display view here
        holder.tvContent.setText(debt.getContent());
        holder.tvFee.setText(Double.toString(debt.getFee()));
        holder.tvDateCreated.setText(Utils.fromDateToDateString("hh:mm dd/MM/yy", debt.getDate()));
        if (debt.getFinanceType() == 0) {
            holder.imvDebtType.setImageResource(R.drawable.ic_red_point);
        } else {

        }
    }

    @Override
    public int getItemCount() {
        if (null == mListDebt) return 0;
        return mListDebt.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvContent;
        TextView tvDateCreated;
        TextView tvFee;
        ImageView imvDebtType;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvContent = (TextView) itemView.findViewById(R.id.tv_debt_content);
            this.tvFee = (TextView) itemView.findViewById(R.id.tv_fee);
            this.tvDateCreated = (TextView) itemView.findViewById(R.id.tv_date_created);
            this.imvDebtType = (ImageView) itemView.findViewById(R.id.imv_debt_type);
        }
    }

    public void setDebtData(List<Debt> listDebt) {
        this.mListDebt = listDebt;
        notifyDataSetChanged();
    }
}
