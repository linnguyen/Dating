package com.example.lin.dollar.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lin.dollar.R;
import com.example.lin.dollar.fragment.adapter.PaperAdapter;

public class HomeFragment extends Fragment {
    ViewPager pager;
    TabLayout tabLayout;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
//        pager = (ViewPager) view.findViewById(R.id.view_paper);
//        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
//        FragmentManager manager = getChildFragmentManager();
//        PaperAdapter adapter = new PaperAdapter(manager);
//        pager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(pager);
//        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.setTabsFromPagerAdapter(adapter);
        return view;

    }
}
