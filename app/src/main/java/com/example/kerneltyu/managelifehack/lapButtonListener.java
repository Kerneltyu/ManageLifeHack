package com.example.kerneltyu.managelifehack;

import android.os.Handler;
import android.view.View;

class lapButtonListener implements View.OnClickListener {
    private Handler handler;
    private Runnable runnable;
    private Counter counter;

    public lapButtonListener(Handler handler, Runnable runnable, Counter counter){
        this.handler = handler;
        this.runnable = runnable;
        this.counter = counter;
    }

    @Override
    public void onClick(View v) {
        //handler.removeCallbacks(runnable);
        counter.lapCounter();
    }
}
