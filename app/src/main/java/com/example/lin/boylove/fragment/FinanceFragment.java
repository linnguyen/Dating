package com.example.lin.boylove.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lin.boylove.R;
import com.example.lin.boylove.fragment.adapter.CustomFragmentPaperAdapter;
import com.example.lin.boylove.interfaces.HomeInterface;

/**
 * Created by lin on 31/08/2017.
 */

public class FinanceFragment extends Fragment {
    private static final String TAG = FinanceFragment.class.getSimpleName();
    private Context context;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private CustomFragmentPaperAdapter customFragmentPaperAdapter;
    private HomeInterface homeInterface;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        customFragmentPaperAdapter = new CustomFragmentPaperAdapter(getChildFragmentManager());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finance, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        viewPager = (ViewPager) view.findViewById(R.id.view_paper);

        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(customFragmentPaperAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                homeInterface.changeFabButton(tab.getText().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

    public void setHomeInterface(HomeInterface homeInterface) {
        this.homeInterface = homeInterface;
    }
}
