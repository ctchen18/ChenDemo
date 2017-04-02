package com.example.user.chendemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainLeftMenuActivity extends AppCompatActivity {

    @BindView(R.id.leftMainMenu)
    LinearLayout leftMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_left_menu);
        ButterKnife.bind(this);
    }
}
