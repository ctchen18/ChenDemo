package com.example.user.chendemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton btn1;
    private ImageButton btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialView();
        initialListener();
    }
    private void initialView(){
        btn1 = (ImageButton) findViewById(R.id.btn1);
    }

    private void initialListener(){
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Button 1 Clicked", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void onBtnTwoCLick(View v){
        Toast.makeText(this,"Button 2 Clicked",Toast.LENGTH_LONG).show();
    }
}
