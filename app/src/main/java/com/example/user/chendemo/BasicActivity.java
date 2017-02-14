package com.example.user.chendemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class BasicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void toActivity(Class name){
        //Intent intent = new Intent(this,ListViewActivity.class);
        //toastLong(name.toString());
        Intent intent = new Intent(this,name);
        startActivity(intent);
    }
    public void toastLong(String content){
        Toast.makeText(this,content,Toast.LENGTH_LONG).show();
    }

    public void toastShort(String content){
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
    }

}
