package com.example.lin.boylove.activity.WeeklyFinanceReport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.lin.boylove.R;

public class DetailFinanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_finance);
        // Hanlde data accompanying notification
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            // get Data from bundle to display on DetailFinanceActivity.
            // The data info could be total_payment, total_income, etc...
            Log.d("BUNDLE", bundle.getString("data"));
        }
    }
}
