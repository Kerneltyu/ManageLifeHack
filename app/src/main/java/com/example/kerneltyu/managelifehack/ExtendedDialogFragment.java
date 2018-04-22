package com.example.kerneltyu.managelifehack;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;

import java.util.ArrayList;

public class ExtendedDialogFragment extends DialogFragment {
    private RecordActivity recordActivity;

    public void setRecordActivity(RecordActivity recordActivity){
        this.recordActivity = recordActivity;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("この内容で保存しますか？")
                .setPositiveButton("はい", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //データベースのところで躓いているらしい
                        //LifeLogDbHelper logDbHelper = new LifeLogDbHelper(recordActivity.getApplicationContext());
                        //SQLiteDatabase db=logDbHelper.getWritableDatabase();
                        /*
                        int counter=1;
                        long newRowId;
                        Cursor cursor = db.query(LifeLogContract.LifeLogEntry.TABLE_NAME, new String[]{"MAX(record_id)"}, null,null,null,null,null);
                        int record_id = 0;
                        record_id = cursor.getInt(cursor.getColumnIndex("MAX(record_id)"));
                        ArrayList<RowData> rowDataArrayList = recordActivity.getRowDataArrayList();
                        for(RowData rd: rowDataArrayList){
                            long[] lapArray = rd.getLapList();
                            ContentValues values = new ContentValues();
                            values.put(LifeLogContract.LifeLogEntry.COLUMN_NAME_TITLE, rd.getTitle());
                            values.put(LifeLogContract.LifeLogEntry.COLUMN_NAME_DESCRIPTION, rd.getDescription());
                            values.put(LifeLogContract.LifeLogEntry.COLUMN_NAME_TIME,rd.getTime_span());
                            values.put(LifeLogContract.LifeLogEntry.COLUMN_NAME_START_TIME, lapArray[(int)rd.getStart_time_index()]);
                            values.put(LifeLogContract.LifeLogEntry.COLUMN_NAME_END_TIME, lapArray[(int)rd.getEnd_time_index()]);
                            values.put(LifeLogContract.LifeLogEntry.COLUMN_NAME_ORDER, counter);
                            values.put(LifeLogContract.LifeLogEntry.COLUMN_NAME_RECORD_ID, record_id);
                            newRowId = db.insert(LifeLogContract.LifeLogEntry.TABLE_NAME, null, values);
                            counter++;
                        }
                        */
                    }
                })
                .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}
