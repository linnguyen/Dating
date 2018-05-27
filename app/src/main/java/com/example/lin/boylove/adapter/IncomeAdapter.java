package com.example.lin.boylove.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lin.boylove.R;
import com.example.lin.boylove.entity.Response.Income;
import com.example.lin.boylove.utilities.Utils;

import java.util.List;

/**
 * Created by ryne on 27/10/2017.
 */

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.ViewHolder> {
    private List<Income> mListIncome;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_income, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Income income = mListIncome.get(position);
        holder.tvNameIncome.setText(income.getName());
        holder.tvValue.setText(Double.toString(income.getValue()));
        holder.tvDateCreated.setText(Utils.fromDateToDateString("hh:mm dd/MM/yy", income.getDate()));
    }

    @Override
    public int getItemCount() {
        if (null == mListIncome) return 0;
        return mListIncome.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameIncome;
        TextView tvValue;
        TextView tvDateCreated;


        public ViewHolder(View itemView) {
            super(itemView);
            tvNameIncome = (TextView) itemView.findViewById(R.id.tv_name_income);
            tvValue = (TextView) itemView.findViewById(R.id.tv_value);
            tvDateCreated = (TextView) itemView.findViewById(R.id.tv_date_created);
        }
    }

    public void setIncomeData(List<Income> listIncome) {
        this.mListIncome = listIncome;
        notifyDataSetChanged();
    }
}
