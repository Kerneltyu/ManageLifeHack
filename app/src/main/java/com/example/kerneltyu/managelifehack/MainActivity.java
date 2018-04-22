package com.example.kerneltyu.managelifehack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int period;
    private long pushCounter;
    private Counter counter;
    private String[] lapSet;

    private Button btn;
    private Button finishButton;
    private Button resetButton;
    private Button recordButton;
    private TextView counterText;
    private RecyclerView lapTextView;
    private RecyclerView.Adapter lapTextAdapter;
    private LinearLayoutManager lapTextManger;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.S", Locale.US);
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable(){
      @Override
      public void run(){
          counter.incrementCounter();
          counterText.setText(dateFormat.format(counter.getCounter()*period));
          handler.postDelayed(this,period);
      }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        period = 100;

        counter = new Counter(period);
        counterText = findViewById(R.id.counterText);
        counterText.setText(dateFormat.format(0));

        lapTextView = findViewById(R.id.lap_text);
        lapTextView.setHasFixedSize(true);
        lapTextManger = new LinearLayoutManager(this);
        lapTextManger.setOrientation(LinearLayoutManager.VERTICAL);
        lapTextManger.setStackFromEnd(true);
        lapTextManger.setReverseLayout(true);
        lapTextView.setLayoutManager(lapTextManger);

        lapTextAdapter = new LapTextAdapter(this, counter.getStrLapList());
        lapTextView.setAdapter(lapTextAdapter);


        btn = findViewById(R.id.start);
        btn.setText("Start");
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                pushCounter++;
                if(pushCounter%2 == 0){
                    handler.removeCallbacks(runnable);
                    counter.lapCounter();
                    recordButton.setEnabled(true);
                    btn.setText(R.string.start_button);
                    lapTextAdapter.notifyDataSetChanged();
                    lapTextView.scrollToPosition(lapTextAdapter.getItemCount()-1);
                    finishButton.setEnabled(false);
                    resetButton.setEnabled(true);
                }
                if(pushCounter%2 == 1){
                    handler.post(runnable);
                    btn.setText(R.string.start_button_reverse);
                    finishButton.setEnabled(true);
                    resetButton.setEnabled(false);
                    recordButton.setEnabled(false);
                }
            }
        });

        finishButton = findViewById(R.id.lap);
        finishButton.setText(R.string.finish_button);
        finishButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(pushCounter%2 == 1){
                    counter.lapCounter();
                    lapTextAdapter.notifyDataSetChanged();
                    lapTextView.scrollToPosition(lapTextAdapter.getItemCount()-1);
                }
            }
        });

        resetButton = findViewById(R.id.reset);
        resetButton.setText(R.string.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(pushCounter%2 == 0) {
                    counter.lapCounter();
                    counter.initializeCounter();
                    counter.resetLog();
                    counterText.setText(new SimpleDateFormat("HH:mm:ss.S", Locale.US).format(0));
                    recordButton.setEnabled(false);
                    lapTextAdapter.notifyDataSetChanged();
                    lapTextView.scrollToPosition(lapTextAdapter.getItemCount()-1);
                }
            }
        });

        recordButton = findViewById(R.id.record);
        recordButton.setText(R.string.record_button);
        recordButton.setEnabled(false);
        recordButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(counter.getStrLapList().size() > 0) {
                    Intent intent = new Intent(getApplication(), RecordActivity.class);
                    intent.putExtra("strCounter", counter.getStrLapList());
                    ArrayList<Long> lapList = counter.getLapList();
                    long[] longLapArray = new long[lapList.size()];
                    for(int i=0; i<lapList.size(); i++){
                        longLapArray[i] = lapList.get(i);
                    }
                    intent.putExtra("longCounter", longLapArray);
                    startActivity(intent);
                }
            }
        });

        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int swipePosition = viewHolder.getAdapterPosition();
                LapTextAdapter adapter = (LapTextAdapter) lapTextView.getAdapter();
                adapter.remove(swipePosition);
                if(! (adapter.getItemCount() > 0)){
                    recordButton.setEnabled(false);
                }
            }
        };
        (new ItemTouchHelper(callback)).attachToRecyclerView(lapTextView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
