package com.example.kerneltyu.managelifehack;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Counter {
    private long counter;
    private int period;
    private ArrayList<Long> lapList;
    private ArrayList<String> strLapList;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.S", Locale.US);

    public Counter(int period){
        counter = 0;
        this.period = period;
        lapList = new ArrayList<Long>();
        strLapList = new ArrayList<String>();
    }

    public long getCounter(){
        return counter;
    }

    public ArrayList<Long> getLapList(){
        return lapList;
    }

    public ArrayList<String> getStrLapList(){
        return strLapList;
    }

    public void initializeCounter(){
        counter = 0;
    }

    public void resetLog(){
        lapList.clear();
        strLapList.clear();
    }

    public void incrementCounter(){
        counter ++;
    }

    public void lapCounter(){
        lapList.add(counter);
        strLapList.add(dateFormat.format(counter*period));
    }

}
