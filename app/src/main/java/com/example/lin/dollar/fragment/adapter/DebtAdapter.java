package com.example.lin.dollar.fragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lin.dollar.R;
import com.example.lin.dollar.entity.Response.Debt;

import java.util.List;

/**
 * Created by ryne on 13/10/2017.
 */

public class DebtAdapter extends RecyclerView.Adapter<DebtAdapter.ViewHolder> {
    private List<Debt> mListDebt;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_debt, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Debt debt = mListDebt.get(position);
        // Processing for display view here
    }

    @Override
    public int getItemCount() {
        if (null == mListDebt) return 0;
        return mListDebt.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvDebtType;
        TextView tvContent;
        TextView tvDateCreated;
        TextView tvFee;
        ImageView imvFinanceType;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void setDebtData(List<Debt> listDebt) {
        this.mListDebt = listDebt;
        notifyDataSetChanged();
    }
}
