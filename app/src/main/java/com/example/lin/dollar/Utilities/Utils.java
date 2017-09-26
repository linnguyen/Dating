package com.example.lin.dollar.Utilities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lin.dollar.R;

/**
 * Created by lin on 20/09/2017.
 */

public class Utils {
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static String getText(EditText editText) {
        return editText.getText().toString().trim();
    }

    // Close android keyboard
    public static void hiddenKeyBoard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    // Show progress dialog (cirle)
    public static void showProgressDialog(ProgressDialog progressDialog, Context context, String message) {
        progressDialog = new ProgressDialog(context, R.style.CustomProgressDialog);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(message);
        progressDialog.show();
    }
}
