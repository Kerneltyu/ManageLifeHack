package com.example.kerneltyu.managelifehack;

import java.util.List;

public class Data {
    private List<RowData> rowDataList;

    public Data(List<RowData> rowDataList){
        this.rowDataList = rowDataList;
    }

    public void addRowData(RowData rowData){
        this.rowDataList.add(rowData);
    }

    public void deleteRowData(int order){
        this.rowDataList.remove(order);
    }
}
