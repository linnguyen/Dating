package com.example.lin.dollar.fragment.view_paper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lin.dollar.R;
import com.example.lin.dollar.fragment.adapter.CustomFragmentPapeAdapter;

/**
 * Created by lin on 31/08/2017.
 */

public class LibraryFragment extends Fragment {
    private static final String TAG = LibraryFragment.class.getSimpleName();

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public LibraryFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        viewPager = (ViewPager) view.findViewById(R.id.view_paper);

        viewPager.setAdapter(new CustomFragmentPapeAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        return  view;
    }
}
