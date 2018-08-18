package com.example.lin.boylove.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lin.boylove.utilities.Constant;

/**
 * Created by lin on 31/08/2017.
 */

public class CustomFragmentPaperAdapter extends FragmentPagerAdapter {
    private static final String TAG = CustomFragmentPaperAdapter.class.getSimpleName();

    private static final int FRAGMENT_COUNT = 3;

    public CustomFragmentPaperAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
//                return new IncomeFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return Constant.TAB_PAYMENT;
            case 1:
                return Constant.TAB_INCOME;
            case 2:
                return Constant.TAB_DEBT;
        }
        return super.getPageTitle(position);
    }
}
