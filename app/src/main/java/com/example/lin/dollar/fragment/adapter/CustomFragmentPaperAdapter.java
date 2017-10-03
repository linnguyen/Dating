package com.example.lin.dollar.fragment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lin.dollar.fragment.viewpaper.Payment.PaymentFragment;
import com.example.lin.dollar.fragment.viewpaper.Income.IncomeFragment;
import com.example.lin.dollar.fragment.viewpaper.Total.TotalFragment;

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
                return new PaymentFragment();
            case 1:
                return new IncomeFragment();
            case 2:
                return new TotalFragment();
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
                return "Payment";
            case 1:
                return "Income";
            case 2:
                return "Total";
        }
        return super.getPageTitle(position);
    }
}
