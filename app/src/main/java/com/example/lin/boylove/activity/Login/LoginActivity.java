package com.example.lin.boylove.activity.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lin.boylove.R;
import com.example.lin.boylove.activity.Home.HomeActivity;
import com.example.lin.boylove.localstorage.SessionManager;
import com.example.lin.boylove.service.WebSocketClient;
import com.example.lin.boylove.utilities.Constant;
import com.example.lin.boylove.utilities.Utils;

import java.net.URI;
import java.net.URISyntaxException;

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
