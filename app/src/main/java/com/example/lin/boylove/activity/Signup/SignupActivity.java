package com.example.lin.boylove.activity.Signup;

import android.content.Intent;
import android.widget.EditText;

import com.example.lin.boylove.R;
import com.example.lin.boylove.activity.DxBaseActivity;
import com.example.lin.boylove.activity.Home.HomeActivity;
import com.example.lin.boylove.activity.Login.LoginActivity;
import com.example.lin.boylove.utilities.Utils;

import butterknife.BindView;
import butterknife.OnClick;

public class SignupActivity extends DxBaseActivity implements SignupView {
    @BindView(R.id.edt_dolax_name)
    EditText edtDolaxName;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.edt_confirm_password)
    EditText edtConfirmPassword;

    private SignupPresenter presenter;

    private String imageUrl;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_signup;
    }

    @Override
    protected void initAttributes() {
        presenter = new SignupPresenterIml(this, context);
    }

    @Override
    protected void initViews() {

    }

    @OnClick(R.id.tv_link_login)
    public void onClickLinkLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_signup)
    public void onClickSignup() {
        presenter.registerAccount(
                Utils.getText(edtDolaxName),
                Utils.getText(edtEmail),
                Utils.getText(edtPassword),
                Utils.getText(edtConfirmPassword),
                imageUrl
        );
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
