package com.example.lin.dollar.dialog.DatePicker;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.example.lin.dollar.R;
import com.example.lin.dollar.other.TimePickerDialogFixedNougatSpinner;

import java.lang.reflect.Field;
import java.util.Calendar;

/**
 * Created by ryne on 28/09/2017.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private UpdateToolbarTitleInterface updateToolbarTitleInterface;
    private String TAG = DatePickerFragment.class.getSimpleName();

    public DatePickerFragment(UpdateToolbarTitleInterface updateToolbarTitleInterface) {
        this.updateToolbarTitleInterface = updateToolbarTitleInterface;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = null;
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        try {

        /* Check if android 7.0 (Nougut) to fix the Holo theme bug.*/
            if (Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.N) {
                Context themedContext = new ContextThemeWrapper(getContext(), R.style.CustomDatePicker);
                datePickerDialog = new FixedHoloDatePickerDialog(
                        themedContext,
                        this,
                        year,
                        month,
                        day
                );
            } else {
                datePickerDialog = new DatePickerDialog(getActivity(), R.style.CustomDatePicker, this, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }

            /* Only display month and year for Date picker */
            Field[] datePickerDialogFields = datePickerDialog.getClass().getDeclaredFields();
            for (Field datePickerDialogField : datePickerDialogFields) {
                if (datePickerDialogField.getName().equals("mDatePicker")) {
                    datePickerDialogField.setAccessible(true);
                    DatePicker datePicker =
                            (DatePicker) datePickerDialogField.get(datePickerDialog);
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        int daySpinnerId =
                                Resources.getSystem().getIdentifier("day", "id", "android");
                        if (daySpinnerId != 0) {
                            View daySpinner = datePicker.findViewById(daySpinnerId);
                            if (daySpinner != null) {
                                //Ẩn cột date, chỉ còn lại month và year
                                daySpinner.setVisibility(View.GONE);
                            }
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            Log.e(TAG, "IllegalAccessException: ", e);
        }
        return datePickerDialog;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
        updateToolbarTitleInterface.updateToolbarTitle(month, year);
    }

    public interface UpdateToolbarTitleInterface {
        void updateToolbarTitle(int month, int year);
    }

}
