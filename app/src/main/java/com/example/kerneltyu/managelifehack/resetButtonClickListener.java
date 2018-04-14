package com.example.kerneltyu.managelifehack;

import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

class resetButtonClickListener implements View.OnClickListener {
    private TextView counterText;
    private Counter counter;

    public resetButtonClickListener(TextView counterText, Counter counter) {
        this.counterText = counterText;
        this.counter = counter;
    }

    @Override
    public void onClick(View v){
        counter.lapCounter();
        counter.initializeCounter();
        counterText.setText(new SimpleDateFormat("HH:mm:ss.S", Locale.US).format(0));
    }
}
