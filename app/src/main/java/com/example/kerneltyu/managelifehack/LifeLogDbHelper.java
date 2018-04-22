package com.example.kerneltyu.managelifehack;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LifeLogDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="LifeLog.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP=",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS "+ LifeLogContract.LifeLogEntry.TABLE_NAME + " ("+
                    LifeLogContract.LifeLogEntry._ID+" INTEGER PRIMARY KEY "+COMMA_SEP+
                    LifeLogContract.LifeLogEntry.COLUMN_NAME_RECORD_ID+" INTEGER "+COMMA_SEP+
                    LifeLogContract.LifeLogEntry.COLUMN_NAME_ORDER+" INTEGER"+COMMA_SEP +
                    LifeLogContract.LifeLogEntry.COLUMN_NAME_TITLE+TEXT_TYPE+COMMA_SEP+
                    LifeLogContract.LifeLogEntry.COLUMN_NAME_DESCRIPTION+TEXT_TYPE+COMMA_SEP+
                    LifeLogContract.LifeLogEntry.COLUMN_NAME_START_TIME+" BLOB"+COMMA_SEP+
                    LifeLogContract.LifeLogEntry.COLUMN_NAME_END_TIME+" BLOB"+COMMA_SEP+
                    LifeLogContract.LifeLogEntry.COLUMN_NAME_TIME+" BLOB"+COMMA_SEP+
                    LifeLogContract.LifeLogEntry.COLUMN_NAME_CREATE_DATE+" BLOB"+")";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS "+ LifeLogContract.LifeLogEntry.TABLE_NAME;

    public LifeLogDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
    }
}
