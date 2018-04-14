package com.example.kerneltyu.managelifehack;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.zip.Inflater;

public class RecordTimeAdapter extends RecyclerView.Adapter<RecordTimeAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private Context context;
    private ArrayList<RowData> dataList;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.S", Locale.US);

    public static class ViewHolder extends RecyclerView.ViewHolder{
        final public View pView;
        public Spinner startTimeSpinner;
        public Spinner endTimeSpinner;
        public TextView timeSpanText;
        public EditText titleText;
        public EditText descriptionText;
        private ArrayList<RowData> dataList;
        private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.S", Locale.US);
        private RecordTimeAdapter recordTimeAdapter;

        public ViewHolder(View v, final ArrayList<RowData> dataList){
            super(v);
            pView = v;
            startTimeSpinner = v.findViewById(R.id.start_time);
            endTimeSpinner = v.findViewById(R.id.end_time);
            timeSpanText = v.findViewById(R.id.time_span);
            titleText = v.findViewById(R.id.title);
            descriptionText = v.findViewById(R.id.description);
            this.dataList = dataList;

            startTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Spinner spinner = pView.findViewById(R.id.end_time);
                    long end_id = 0l;
                    if(spinner != null) {
                        end_id = spinner.getSelectedItemId();
                    }
                    Log.d("output",String.valueOf(position));
                    RowData rowData = dataList.get(getAdapterPosition());  //どのデータもLapListは同じなため最初の要素に固定
                    Log.d("output", "StartId:"+String.valueOf(id)+" EndId"+String.valueOf(end_id));
                    rowData.setTime_span(id, end_id);
                    TextView tv = pView.findViewById(R.id.time_span);
                    tv.setText(dateFormat.format(rowData.getTime_span()));
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
                    RowData rowData = dataList.get(getAdapterPosition());  //どのデータもLapListは同じなため最初の要素に固定
                    Log.d("output", "StartId:"+String.valueOf(start_id)+" EndId"+String.valueOf(id));
                    rowData.setTime_span(start_id, id);
                    TextView tv = pView.findViewById(R.id.time_span);
                    tv.setText(dateFormat.format(rowData.getTime_span()));
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
        return new ViewHolder(mInflater.inflate(R.layout.recycler_item, parent, false),dataList);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArrayList<String> selectable = dataList.get(position).getStrLapList();
        setSpinner(holder.startTimeSpinner, selectable);
        setSpinner(holder.endTimeSpinner,selectable);
        holder.timeSpanText.setText(dateFormat.format(dataList.get(position).getTime_span()));
        //setSpinner(holder);
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
