package com.example.lin.dollar.dialog.DatePicker;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.view.ContextThemeWrapper;
import android.widget.DatePicker;

import com.example.lin.dollar.R;
import com.example.lin.dollar.other.TimePickerDialogFixedNougatSpinner;

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
        // DatePick isn't still fixed here..!
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
