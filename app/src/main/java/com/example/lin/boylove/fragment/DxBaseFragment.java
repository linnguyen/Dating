package com.example.lin.boylove.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lin.boylove.utilities.Utils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ryne on 26/10/2017.
 */

public abstract class DxBaseFragment extends Fragment {
    /*
     * Container fragment
     */

    protected Context mContext;

    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResource(), container, false);
        unbinder = ButterKnife.bind(this, view);
        initAttributes();
        initViews();
        return view;
    }

    protected abstract int getLayoutResource();

    protected abstract void initViews();

    protected abstract void initAttributes();

    protected void showMessage(String msg) {
        Utils.showToast(mContext, msg);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
