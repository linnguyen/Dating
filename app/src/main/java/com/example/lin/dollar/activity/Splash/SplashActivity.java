package com.example.lin.dollar.activity.Splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.lin.dollar.R;
import com.example.lin.dollar.activity.Login.LoginActivity;
import com.example.lin.dollar.activity.MainActivity;
import com.example.lin.dollar.localstorage.SessionManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO--Add an image for slash screen here
//        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                directActivity();
            }
        }, 1000);
    }

    private void directActivity() {
        Intent intent = new Intent(this, TextUtils.isEmpty(SessionManager.getInstance(this).getToken()) ? LoginActivity.class : MainActivity.class);
        startActivity(intent);
        finish();
    }
}
