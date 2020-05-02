package com.example.expensemanager;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_Amount = "amount";
        public static final String COLUMN_NAME_Category = "category";
        public static final String COLUMN_NAME_Date = "date";
    }
}