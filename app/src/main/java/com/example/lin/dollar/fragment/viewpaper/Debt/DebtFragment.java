package com.example.lin.dollar.fragment.viewpaper.Debt;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lin.dollar.R;
import com.example.lin.dollar.entity.Response.Debt;
import com.example.lin.dollar.fragment.adapter.DebtAdapter;
import com.example.lin.dollar.other.VegalayoutManager.VegaLayoutManager;
import com.example.lin.dollar.utilities.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DebtFragment extends Fragment {
    private Context context;
    private RecyclerView rvDebt;

    private DebtAdapter mDebtAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_debt, container, false);
        rvDebt = (RecyclerView) view.findViewById(R.id.rv_debt);
        /* Still clear for this piece of code
        View positionView = view.findViewById(R.id.main_position_view);
        boolean immerse = Utils.immerseStatusBar(getActivity());
        boolean darkMode = Utils.setDarkMode(getActivity());
        if (immerse) {
            ViewGroup.LayoutParams lp = positionView.getLayoutParams();
            lp.height = Utils.getStatusBarHeight(context);
            positionView.setLayoutParams(lp);
            if (!darkMode) {
                positionView.setBackgroundColor(Color.BLACK);
            }
        } else {
            positionView.setVisibility(View.GONE);
        }
        */
        setupListDebt();
        // fake Debt
        fakeDebt();
        return view;
    }

    private void setupListDebt() {
        rvDebt.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        rvDebt.setLayoutManager(new VegaLayoutManager());
        mDebtAdapter = new DebtAdapter();
        rvDebt.setAdapter(mDebtAdapter);
    }

    private void fakeDebt() {
        List<Debt> debtList = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < 100; i++) {
            Debt debt = new Debt("whold", 100000, c.getTime(), 1, 100);
            debtList.add(debt);
        }
        mDebtAdapter.setDebtData(debtList);
    }
}
