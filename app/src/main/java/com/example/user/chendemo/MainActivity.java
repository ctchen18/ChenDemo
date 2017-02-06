package com.example.user.chendemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton btn1;
    private ImageButton btn2;
    private ImageButton btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialView();
        initialListener();
    }
    private void initialView(){
        btn1 = (ImageButton) findViewById(R.id.btn1);
        btn3 = (ImageButton) findViewById(R.id.btn3);
    }

/*    private void View.OnClickListener btnListener1(){

    }*/

    private void initialListener(){
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(),"Button 1 Clicked", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), ViewPagerActivity.class);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ListViewActivity.class);
                startActivity(intent);
            }
        });
    }
    public void onBtnTwoCLick(View v){
        Toast.makeText(this,"Button 2 Clicked",Toast.LENGTH_LONG).show();
    }

}
