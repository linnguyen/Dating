package com.example.lin.dollar.Utilities;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by lin on 20/09/2017.
 */

public class Utils {
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static String getText(EditText editText){
        return editText.getText().toString().trim();
    }
}
