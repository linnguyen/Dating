package com.example.lin.dollar.fragment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lin.dollar.fragment.view_paper.ChargeFragment;
import com.example.lin.dollar.fragment.view_paper.IncomeFragment;

/**
 * Created by lin on 31/08/2017.
 */

public class CustomFragmentPapeAdapter extends FragmentPagerAdapter {
    private static final String TAG = CustomFragmentPapeAdapter.class.getSimpleName();

    private static final int FRAGMENT_COUNT = 2;

    public CustomFragmentPapeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ChargeFragment();
            case 1:
                return new IncomeFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "INCOME";
            case 1:
                return "CHARGE";
        }
        return super.getPageTitle(position);
    }
}
