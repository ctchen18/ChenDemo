package com.example.user.chendemo;

import android.os.Handler;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimerActivity extends AppCompatActivity {

    @BindView(R.id.timer_et)
    EditText editText;

    private int time;

    Handler mHandler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            time--;
            if(time>0){
                editText.setText(String.valueOf(time));
                mHandler.postDelayed(this,1000);
                //mHandler.post(this);      //no delays
                //using handler with runnable, multi-thread and loops is implemented. If used in the onclick method, only 1 runnable will be invoked
            }
        }
    };

    @BindView(R.id.timer_bt)
    Button timerButton;

    View.OnClickListener start = new View.OnClickListener(){
        @Override
        public void onClick(View v){

            time= Integer.valueOf(editText.getText().toString());
            editText.setEnabled(false);
            timerButton.setText("Stop");
            timerButton.setOnClickListener(stop);
            mHandler.postDelayed(runnable,1000);
        }
    };
    View.OnClickListener stop = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            timerButton.setText("Reset");
            timerButton.setOnClickListener(reset);
            mHandler.removeCallbacks(runnable);
        }
    };
    View.OnClickListener reset = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            timerButton.setText("Start");
            timerButton.setOnClickListener(start);
            editText.setEnabled(true);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        ButterKnife.bind(this);
        timerButton.setOnClickListener(start);

    }
}
