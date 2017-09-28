package com.example.lin.dollar.activity.AddPayment;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.lin.dollar.entity.Response.Payment;
import com.example.lin.dollar.R;
import com.example.lin.dollar.Utilities.Utils;

public class AddPaymentActivity extends AppCompatActivity implements AddPaymentView, View.OnClickListener {
    private Context context;
    private ImageView imvCloseAddPayment;
    private EditText edtPaymentName;
    private EditText edtPaymentFee;
    private Button btnCreatePayment;
    private ProgressBar progressBar;

    private AddPaymentPresenter addPaymentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);
        context = getApplicationContext();
        addPaymentPresenter = new AddPaymentPresenterIml(context, this);
        imvCloseAddPayment = (ImageView) findViewById(R.id.imv_close_add_payment);
        edtPaymentName = (EditText) findViewById(R.id.edt_payment_name);
        edtPaymentFee = (EditText) findViewById(R.id.edt_payment_fee);
        btnCreatePayment = (Button) findViewById(R.id.btn_create_payment);
        progressBar = (ProgressBar) findViewById(R.id.pb_adding);
        btnCreatePayment.setOnClickListener(this);
        imvCloseAddPayment.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imv_close_add_payment:
                finish();
                break;
            case R.id.btn_create_payment:
                Payment payment = getPaymentObject();
                addPaymentPresenter.addPayment(context, payment);
                finish();
                break;
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void showMessage(String message) {
        Utils.showToast(context, message);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    private Payment getPaymentObject() {
        //TODO -- Validate form here
        return new Payment(Utils.getText(edtPaymentName), Double.valueOf(Utils.getText(edtPaymentFee)));
    }
}
