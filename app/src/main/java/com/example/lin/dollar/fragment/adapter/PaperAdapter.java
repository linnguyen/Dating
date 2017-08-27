package com.example.lin.dollar.fragment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.lin.dollar.fragment.view_paper.ChargeFragment;
import com.example.lin.dollar.fragment.view_paper.IncomeFragment;

/**
 * Created by lin on 20/08/2017.
 */

public class PaperAdapter extends FragmentStatePagerAdapter {
    public PaperAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position) {
            case 0:
                frag = new ChargeFragment();
                break;
            case 1:
                frag = new IncomeFragment();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Income";
                break;
            case 1:
                title = "Charge";
                break;
        }

        return title;
    }
}
