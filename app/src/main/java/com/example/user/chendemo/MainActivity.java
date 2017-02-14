package com.example.user.chendemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.util.Log;

import com.example.user.chendemo.util.UtilLog;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BasicActivity {
    private ImageButton btn1;
    //private ImageButton btn2;
    private ImageButton btn3;

    @OnClick(R.id.btn2)
    public void button2Click(){
        toActivity(DialogActivity.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialView();
        initialListener();
        ButterKnife.bind(this);   //how to use butterknife
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
                toActivity(ListViewActivity.class);
                //Intent intent = new Intent(v.getContext(),ListViewActivity.class);
                //startActivity(intent);
            }
        });
    }
    public void onBtnTwoCLick(View v){
        //Toast.makeText(this,"Button 2 Clicked",Toast.LENGTH_LONG).show();
        toastLong("Button 2 clicked");
        UtilLog.logD("testD","Toast");
        //Log.d("testD","Toast");

    }

}
