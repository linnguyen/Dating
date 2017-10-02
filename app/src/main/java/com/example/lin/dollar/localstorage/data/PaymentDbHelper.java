package com.example.lin.dollar.localstorage.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ryne on 02/10/2017.
 */

public class PaymentDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "payment.db";

    private static final int DATABASE_VERSION = 1;

    public PaymentDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_PAYMENT_TABLE = "CREATE TABLE " +
                PaymentContract.PaymentEntry.TABLE_NAME + " (" +
                PaymentContract.PaymentEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PaymentContract.PaymentEntry.COLUMN_PAYMENT_ID + "INTEGER NOT NULL, " +
                PaymentContract.PaymentEntry.COLUMN_PAYMENT_NAME + "TEXT NOT NULL, " +
                PaymentContract.PaymentEntry.COLUMN_PAYMENT_FEE + "REAL NOT NULL, " +
                PaymentContract.PaymentEntry.COLUMN_PAYMENT_DATE + "TEXT NOT NULL, " +
                PaymentContract.PaymentEntry.COLUMN_USER_ID + "INTEGER NOT NULL" + ");";
        sqLiteDatabase.execSQL(SQL_CREATE_PAYMENT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PaymentContract.PaymentEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
