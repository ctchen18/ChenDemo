package com.example.user.chendemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;


import com.example.user.chendemo.adapter.ListViewAdapter;
import com.example.user.chendemo.util.UtilLog;

public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private ArrayList<String> listResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listResult = new ArrayList<String>();
        createFakeResult();

        initialView();
    }

    private void createFakeResult(){
        listResult.add("A");
        listResult.add("B");
        listResult.add("C");
        listResult.add("D");
        listResult.add("E");
        listResult.add("F");
        listResult.add("G");
        listResult.add("H");
        listResult.add("I");
        listResult.add("J");
        listResult.add("K");
        listResult.add("L");
        listResult.add("M");
        listResult.add("N");
        listResult.add("O");
        listResult.add("P");
        listResult.add("Q");
    }
    private void initialView(){
        listView =(ListView) findViewById(R.id.list_view);
        View view = getLayoutInflater().inflate(R.layout.list_view_header,null); //null for view group
        LinearLayout listViewHeader =(LinearLayout) view.findViewById(R.id.list_view_header);

        ListViewAdapter listViewAdapter = new ListViewAdapter(this,listResult);
        listView.addHeaderView(listViewHeader);

        //pure java code
        TextView tv =new TextView(this);
        tv.setText("We have no more content");
        tv.setTextSize(28);
        tv.setGravity(Gravity.CENTER);
        listView.addFooterView(tv);


        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"Listview was clicked at position:"+position,Toast.LENGTH_LONG).show();
        UtilLog.logD("testListViewActivity", String.valueOf(position));
    }
}
