package com.example.lin.boylove.activity.Signup;

import android.content.Intent;

import com.example.lin.boylove.R;
import com.example.lin.boylove.activity.DxBaseActivity;
import com.example.lin.boylove.activity.Login.LoginActivity;

import butterknife.OnClick;

public class SignupActivity extends DxBaseActivity {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_signup;
    }

    @Override
    protected void initAttributes() {

    }

    @Override
    protected void initViews() {

    }

    @OnClick(R.id.tv_link_login)
    public void onClickLinkLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
