package com.example.user.chendemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class launch_mode_c extends AppCompatActivity {

    @OnClick(R.id.a_to_a)
    public void buttonAClick(){
        Intent intent= new Intent(this,launch_mode_a.class);
        //toActivity(DialogActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.a_to_b)
    public void buttonBClick(){
        Intent intent= new Intent(this,launch_mode_b.class);
        //toActivity(DialogActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.a_to_c)
    public void buttonCClick(){
        Intent intent= new Intent(this,launch_mode_c.class);
        //toActivity(DialogActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.a_to_d)
    public void buttonDClick(){
        Intent intent= new Intent(this,launch_mode_d.class);
        //toActivity(DialogActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode_c);
        ButterKnife.bind(this);
    }
}
