package com.example.lin.boylove.activity.Splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.lin.boylove.activity.Login.LoginActivity;
import com.example.lin.boylove.activity.Home.HomeActivity;
import com.example.lin.boylove.localstorage.SessionManager;

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
        Intent intent = new Intent(this, TextUtils.isEmpty(SessionManager.getInstance(this).getToken()) ? LoginActivity.class : HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
