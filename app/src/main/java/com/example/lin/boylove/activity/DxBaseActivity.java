package com.example.lin.boylove.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ryne on 19/10/2017.
 */

public abstract class DxBaseActivity extends AppCompatActivity {
    protected Context context;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getApplicationContext();
        setContentView(getLayoutRes());
        unbinder = ButterKnife.bind(this);
        initAttributes();
        initViews();
    }

    protected abstract int getLayoutRes();

    protected abstract void initAttributes();

    protected abstract void initViews();

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }
}
