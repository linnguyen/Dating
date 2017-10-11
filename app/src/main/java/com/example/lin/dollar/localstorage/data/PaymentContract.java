package com.example.lin.dollar.localstorage.data;

import android.provider.BaseColumns;

/**
 * Created by ryne on 02/10/2017.
 */

public class PaymentContract {
    public final static class PaymentEntry implements BaseColumns {
        // TABLE_NAME -> finance
        public static final String TABLE_NAME = "finance";
        // COLUMN_PAYMENT_ID -> id
        public static final String COLUMN_PAYMENT_ID = "id";
        // COLUMN_PAYMENT_NAME -> name
        public static final String COLUMN_PAYMENT_NAME = "name";
        // COLUMN_PAYMENT_FEE -> fee
        public static final String COLUMN_PAYMENT_FEE = "fee";
        // COLUMN_PAYMENT_DATE -> date
        public static final String COLUMN_PAYMENT_DATE = "date";
        // COLUMN_USER_ID -> user_id
        public static final String COLUMN_USER_ID = "user_id";

    }
}
