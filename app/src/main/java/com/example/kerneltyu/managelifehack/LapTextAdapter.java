package com.example.kerneltyu.managelifehack;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class LapTextAdapter extends RecyclerView.Adapter<LapTextAdapter.ViewHolder> {
    @NonNull
    private LayoutInflater mInflater;
    private ArrayList<String> lapTextList;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;

        public ViewHolder(View v){
            super(v);
            mTextView = v.findViewById(R.id.list_item_text);
        }
    }

    public LapTextAdapter(Context context, ArrayList<String> lapTextList){
        this.mInflater = LayoutInflater.from(context);
        this.lapTextList = lapTextList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTextView.setText(lapTextList.get(position));
    }

    @Override
    public int getItemCount() {
        return lapTextList.size();
    }

    public void remove(int position){
        lapTextList.remove(position);
        notifyItemRemoved(position);
    }
}
