package com.example.kerneltyu.managelifehack;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class RecordTimeAdapter extends RecyclerView.Adapter<RecordTimeAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private Context context;
    private ArrayList<RowData> dataList;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        final public View pView;
        private Spinner startTimeSpinner;
        private Spinner endTimeSpinner;
        private TextView timeSpanText;
        private EditText titleText;
        private EditText descriptionText;
        private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.S", Locale.US);
        private long timeSpan;
        public Bundle bundle;

        public ViewHolder(View v, final RecordTimeAdapter recordTimeAdapter){
            super(v);
            pView = v;
            startTimeSpinner = v.findViewById(R.id.start_time);
            endTimeSpinner = v.findViewById(R.id.end_time);
            timeSpanText = v.findViewById(R.id.time_span);
            titleText = v.findViewById(R.id.title);
            descriptionText = v.findViewById(R.id.description);
            timeSpan = 0l;

            //titleのイベントリスナー
            titleText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    RowData rowData = recordTimeAdapter.dataList.get(getAdapterPosition());
                    rowData.setTitle(s.toString());
                }
            });

            //descriptionのイベントリスナー
            descriptionText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    RowData rowData = recordTimeAdapter.dataList.get(getAdapterPosition());
                    rowData.setDescription(s.toString());
                }
            });

            startTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Spinner spinner = pView.findViewById(R.id.end_time);
                    long end_id = 0l;
                    if(spinner != null) {
                        end_id = spinner.getSelectedItemId();
                    }
                    Log.d("output",String.valueOf(position));
                    RowData rowData = recordTimeAdapter.dataList.get(getAdapterPosition());  //どのデータもLapListは同じなため最初の要素に固定
                    Log.d("output", "StartId:"+String.valueOf(id)+" EndId"+String.valueOf(end_id));
                    rowData.setTime_span(id,end_id);
                    timeSpanText.setText(dateFormat.format(rowData.getTime_span()*100));
                    rowData.setStart_time_index(id);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            endTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Spinner spinner = pView.findViewById(R.id.start_time);
                    long start_id = 0l;
                    if(spinner != null) {
                        start_id = spinner.getSelectedItemId();
                    }
                    RowData rowData = recordTimeAdapter.dataList.get(getAdapterPosition());  //どのデータもLapListは同じなため最初の要素に固定
                    rowData.setEnd_time_index(id);
                    Log.d("output", "StartId:"+String.valueOf(start_id)+" EndId"+String.valueOf(id));
                    rowData.setTime_span(start_id,id);
                    String s = dateFormat.format((rowData.getTime_span()*100));
                    timeSpanText.setText(s);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
    }

    public RecordTimeAdapter(Context context, ArrayList<RowData> dataList){
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(mInflater.inflate(R.layout.recycler_item, parent, false),this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RowData rowData = dataList.get(position);
        ArrayList<String> selectable = rowData.getStrLapList();
        setSpinner(holder.startTimeSpinner, selectable);
        setSpinner(holder.endTimeSpinner, selectable);
        //holder.timeSpanText.setText(dateFormat.format(dataList.get(position).getTime_span()));
        //setSpinner(holder);
        holder.startTimeSpinner.setSelection((int)rowData.getStart_time_index());
        holder.endTimeSpinner.setSelection((int)rowData.getEnd_time_index());
        holder.titleText.setText(rowData.getTitle());
        holder.descriptionText.setText(rowData.getDescription());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setSpinner(Spinner spinner, ArrayList<String> stringList){
        ArrayAdapter adapter= new ArrayAdapter(context, R.layout.spinner_item, stringList);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

}
