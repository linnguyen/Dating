package com.example.lin.dollar.dialog.DatePicker;

import android.app.DatePickerDialog;

/**
 * Created by ryne on 13/10/2017.
 */

public interface TimeManagement {
    TimeManagement dialogDatePicker(DatePickerDialog.OnDateSetListener onDateSetListener);

    void showDatePickerDialog();
}
