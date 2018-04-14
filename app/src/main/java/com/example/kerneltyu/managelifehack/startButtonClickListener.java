package com.example.kerneltyu.managelifehack;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;

class startButtonClickListener implements View.OnClickListener {

    private Handler handler;
    private Runnable runnable;
    private long pushCounter;

    public startButtonClickListener(Handler handler, Runnable runnable){
        this.handler = handler;
        this.runnable = runnable;
        this.pushCounter = 0;
    }

    public void onClick(View v){
        pushCounter++;
        if(pushCounter%2 == 0){
            handler.removeCallbacks(runnable);

        }
        if(pushCounter%2 == 1){
            handler.post(runnable);
        }

    }
}
