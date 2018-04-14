package com.example.kerneltyu.managelifehack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
        RecordTimeAdapter recordTimeAdapter = new RecordTimeAdapter(this, rowDataArrayList);
        recyclerView.setAdapter(recordTimeAdapter);
    }

    public void setSpinner(Spinner spinner, ArrayList<String> strList){
        ArrayAdapter adapter= new ArrayAdapter(this, android.R.layout.simple_spinner_item, strList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(adapter);
    }
}
