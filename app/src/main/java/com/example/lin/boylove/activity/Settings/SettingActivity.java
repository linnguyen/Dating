package com.example.lin.boylove.activity.Settings;

import android.content.Intent;

import com.example.lin.boylove.R;
import com.example.lin.boylove.activity.DxBaseActivity;
import com.example.lin.boylove.activity.Login.LoginActivity;
import com.example.lin.boylove.localstorage.SessionManager;

import butterknife.OnClick;

public class SettingActivity extends DxBaseActivity
        implements SettingView {

    private SettingPresenter presenter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initAttributes() {
        presenter = new SettingPresenterIml(this, context);
    }

    @Override
    protected void initViews() {

    }

    @OnClick(R.id.imv_logout)
    public void onClickLogout() {
        presenter.logout();

        // clear token
        SessionManager.getInstance(context).clear();

        // navigate to login
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @OnClick(R.id.imv_back)
    public void onClickBack() {
        finish();
    }
}
