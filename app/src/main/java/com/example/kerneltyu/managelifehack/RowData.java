package com.example.kerneltyu.managelifehack;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class RowData {
    private ArrayList<String> strLapList;
    private long[] lapList;
    private long time_span;
    private String title;
    private String description;


    public RowData(ArrayList<String> strLapList, long[] lapList){
        this.strLapList = strLapList;
        this.lapList = lapList;
        time_span = 0l;
        title="";
        description="";
    }

    public ArrayList<String> getStrLapList(){
        return strLapList;
    }

    public long getTime_span(){
        return time_span;
    }

    public void setTime_span(long start_key, long end_key){
        long temp = calcTime_span(start_key, end_key);
        if(temp < 0){
            this.time_span=0l;
        }else {
            this.time_span = temp;
        }
    }

    private long calcTime_span(long start_key, long end_key){
        return lapList[(int)end_key]-lapList[(int)start_key];
    }
}
