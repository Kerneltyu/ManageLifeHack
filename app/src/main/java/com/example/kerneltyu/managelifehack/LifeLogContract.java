package com.example.kerneltyu.managelifehack;

import android.provider.BaseColumns;

public final class LifeLogContract {
    private LifeLogContract(){}

    public static class LifeLogEntry implements BaseColumns{
        public static final String TABLE_NAME="life_log";
        public static final String COLUMN_NAME_RECORD_ID = "record_id";
        public static final String COLUMN_NAME_ORDER="order_log";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION ="subscription";
        public static final String COLUMN_NAME_START_TIME="start_time";
        public static final String COLUMN_NAME_END_TIME="end_time";
        public static final String COLUMN_NAME_TIME="time";
        public static final String COLUMN_NAME_CREATE_DATE="create_date";
    }
}
