package com.example.lin.dollar.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.example.lin.dollar.R;

import java.util.Calendar;

/**
 * Created by ryne on 28/09/2017.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private NavigateToDetailFinanceActivity navigateToDetailFinanceActivity;

    public DatePickerFragment(NavigateToDetailFinanceActivity navigateToDetailFinanceActivity) {
        this.navigateToDetailFinanceActivity = navigateToDetailFinanceActivity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), R.style.CustomDatePicker, this, year, month, day);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return datePickerDialog;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
        navigateToDetailFinanceActivity.onNavigateToDetailFinanceActivity();
    }

    public interface NavigateToDetailFinanceActivity {
        void onNavigateToDetailFinanceActivity();
    }
}
