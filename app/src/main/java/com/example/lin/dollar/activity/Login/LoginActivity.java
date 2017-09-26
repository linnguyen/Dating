package com.example.lin.dollar.activity.Login;

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lin.dollar.R;
import com.example.lin.dollar.Utilities.Utils;
import com.example.lin.dollar.activity.MainActivity;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {
    private Context context;
    private ProgressDialog progressDialog;

    private LoginPresenter loginPresenter;

    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = getApplicationContext();
        loginPresenter = new LoginPresenterIml(this, context);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                Utils.hiddenKeyBoard(this);
                loginPresenter.validateCredentials(Utils.getText(edtEmail), Utils.getText(edtPassword));
                break;
        }
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
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
