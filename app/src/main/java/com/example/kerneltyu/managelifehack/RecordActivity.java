package com.example.kerneltyu.managelifehack;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class RecordActivity extends AppCompatActivity {
    private Spinner startTimeSpinner;
    private Spinner endTimeSpinner;
    private TextView timeSpanText;
    private EditText titleText;
    private EditText descriptionText;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;

    private ImageButton button_add;
    private ImageButton button_save;

    private Intent intent;
    private ArrayList<String> strCounterList;
    private long[] counterList;
    private ArrayList<RowData> rowDataArrayList;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.S", Locale.US);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        intent = getIntent();
        strCounterList = intent.getStringArrayListExtra("strCounter");
        counterList = new long[strCounterList.size()];
        counterList = intent.getLongArrayExtra("longCounter");

        recyclerView = findViewById(R.id.recyclerTimeView);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        RowData rowData = new RowData(strCounterList,counterList);
        rowDataArrayList = new ArrayList<RowData>();
        rowDataArrayList.add(rowData);
        final RecordTimeAdapter recordTimeAdapter = new RecordTimeAdapter(this, rowDataArrayList);
        recyclerView.setAdapter(recordTimeAdapter);

        button_add = findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                RowData rowData = new RowData(strCounterList, counterList);
                rowDataArrayList.add(rowData);
                Log.d("output", String.valueOf(recordTimeAdapter.getItemCount()));
                recordTimeAdapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(recordTimeAdapter.getItemCount()-1);

            }
        });

        button_save = findViewById(R.id.button_save);
        button_save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment newFragment = new ExtendedDialogFragment();
                newFragment.show(getFragmentManager(), "test");


/*
                new AlertDialog.Builder(getApplication())
                        .setTitle("確認")
                        .setMessage("この内容で保存しますか？")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                /*
                                LifeLogDbHelper logDbHelper = new LifeLogDbHelper(getApplicationContext());
                                SQLiteDatabase db=logDbHelper.getWritableDatabase();
                                int counter=1;
                                long newRowId;
                                Cursor cursor = db.query(LifeLogContract.LifeLogEntry.TABLE_NAME, new String[]{"MAX(record_id)"}, null,null,null,null,null);
                                int record_id = 0;
                                record_id = cursor.getInt(cursor.getColumnIndex("MAX(record_id)"));
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

                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
                        */
            }
        });
    }

    public ArrayList<RowData> getRowDataArrayList() {
        return rowDataArrayList;
    }
}
