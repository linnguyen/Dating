package com.example.lin.dollar.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lin.dollar.utilities.Utils;

/**
 * Created by ryne on 26/10/2017.
 */

public abstract class DxBaseFragment extends Fragment {
    /*
     * Container fragment
     */

    protected Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResource(), container, false);
        initViews(view);
        return view;
    }

    protected abstract int getLayoutResource();

    protected abstract void initViews(View view);

    protected void showMessage(String msg) {
        Utils.showToast(mContext, msg);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
