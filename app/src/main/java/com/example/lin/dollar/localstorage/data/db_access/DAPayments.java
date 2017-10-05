package com.example.lin.dollar.localstorage.data.db_access;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.lin.dollar.utilities.Utils;
import com.example.lin.dollar.entity.Response.Payment;
import com.example.lin.dollar.localstorage.data.PaymentContract;

/**
 * Created by ryne on 02/10/2017.
 */

public class DAPayments {
    private ContentValues getContentValues(Payment payment) {
        ContentValues values = new ContentValues();
        values.put(PaymentContract.PaymentEntry.COLUMN_PAYMENT_ID, payment.getId());
        values.put(PaymentContract.PaymentEntry.COLUMN_PAYMENT_NAME, payment.getName());
        values.put(PaymentContract.PaymentEntry.COLUMN_PAYMENT_FEE, payment.getFee());
        values.put(PaymentContract.PaymentEntry.COLUMN_PAYMENT_DATE, Utils.fromDateToMilliseconds(payment.getDate()));
        values.put(PaymentContract.PaymentEntry.COLUMN_USER_ID, payment.getUser_id());
        return values;
    }

    private Payment getFromCursor(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(PaymentContract.PaymentEntry.COLUMN_PAYMENT_ID));
        String name = cursor.getString(cursor.getColumnIndex(PaymentContract.PaymentEntry.COLUMN_PAYMENT_NAME));
        double fee = cursor.getDouble(cursor.getColumnIndex(PaymentContract.PaymentEntry.COLUMN_PAYMENT_FEE));
        long dateMilliseconds = cursor.getLong(cursor.getColumnIndex(PaymentContract.PaymentEntry.COLUMN_PAYMENT_DATE));
        int userId = cursor.getInt(cursor.getColumnIndex(PaymentContract.PaymentEntry.COLUMN_USER_ID));
        return new Payment(id, name, fee, Utils.fromMillisecondsToDate("hh:mm dd/MM/yy", dateMilliseconds), userId);
    }


}
