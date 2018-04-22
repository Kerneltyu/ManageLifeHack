package com.example.kerneltyu.managelifehack;

import android.os.Bundle;

import java.util.ArrayList;

public class RowData {
    private ArrayList<String> strLapList;
    private long[] lapList;
    private long time_span;
    private String title;
    private String description;
    private long start_time_index;
    private long end_time_index;

    public RowData(ArrayList<String> strLapList, long[] lapList){
        this.strLapList = strLapList;
        this.lapList = lapList;
        time_span = 0l;
        title="";
        description="";
        start_time_index =0l;
        end_time_index=0l;
    }

    public long[] getLapList()
    {
        return lapList;
    }

    public ArrayList<String> getStrLapList(){
        return strLapList;
    }

    public long getTime_span(){
        return time_span;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public long getStart_time_index(){
        return start_time_index;
    }

    public long getEnd_time_index(){
        return end_time_index;
    }

    public void setTime_span(long start_key, long end_key){
        long temp = calcTime_span(start_key, end_key);
        if(temp < 0){
            this.time_span=0l;
        }else {
            this.time_span = temp;
        }
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setStart_time_index(Long start_time_index){
        this.start_time_index = start_time_index;
    }

    public void setEnd_time_index(Long end_time_index){
        this.end_time_index = end_time_index;
    }

    private long calcTime_span(long start_key, long end_key){
        return lapList[(int)end_key]-lapList[(int)start_key];
    }
}
