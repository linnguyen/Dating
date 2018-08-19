package com.example.lin.boylove.activity.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;

import com.example.lin.boylove.R;
import com.example.lin.boylove.activity.DxBaseActivity;
import com.example.lin.boylove.activity.Home.HomeActivity;
import com.example.lin.boylove.activity.Signup.SignupActivity;
import com.example.lin.boylove.utilities.Utils;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends DxBaseActivity implements LoginView {
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_password)
    EditText edtPassword;

    private ProgressDialog progressDialog;
    private LoginPresenter loginPresenter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initAttributes() {
        loginPresenter = new LoginPresenterIml(this, context);
    }

    @Override
    protected void initViews() {

    }

    @OnClick(R.id.btn_login)
    public void onClickLogin() {
        Utils.hiddenKeyBoard(this);
        loginPresenter.validateCredentials(Utils.getText(edtEmail), Utils.getText(edtPassword));
    }

    @OnClick(R.id.tv_link_signup)
    public void onClickSignupLink() {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }


    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this, R.style.CustomProgressDialog);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.dialog_login));
        progressDialog.show();
    }

    @Override
    public void showMessage(String message) {
        Utils.showToast(context, message);
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void navigateToHome() {
//        // open socket connect when user enters chat room
//        connectToWebsocket();

        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

//    private void connectToWebsocket() {
//        try {
//            socket = new WebSocketClient(new
//                    URI(Constant.Config.SOCKET_URL+ SessionManager.getInstance(getApplicationContext()).getToken()));
//            socket.setListener(this);
//            socket.connect();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//    }
}
