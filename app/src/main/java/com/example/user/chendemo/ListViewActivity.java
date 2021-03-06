package com.example.user.chendemo;

import android.content.Intent;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;


import com.example.user.chendemo.adapter.ListViewAdapter;
import com.example.user.chendemo.adapter.ViewPagerAdapter;
import com.example.user.chendemo.listViewFragments.HeaderFragment;
import com.example.user.chendemo.util.UtilLog;

import butterknife.ButterKnife;


public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private ArrayList<String> listResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ButterKnife.bind(this);
        listResult = new ArrayList<String>();
        createFakeResult();

        initialView();
    }

    private void createFakeResult(){
        listResult.add("AAAAAAAAAAA");
        listResult.add("BBBBBBBB");
        listResult.add("CCCCCCCCCCC");
        listResult.add("DDDDDDDDDDDDDDD");
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

        ViewPager viewPager;
        ArrayList<Fragment> fragmentArrayList = new ArrayList<Fragment>();
        viewPager = (ViewPager) view.findViewById(R.id.view_pager_header);

        HeaderFragment current = new HeaderFragment();
        current.setScaleType(ImageView.ScaleType.CENTER);
        fragmentArrayList.add(current);

        current = new HeaderFragment();
        current.setScaleType(ImageView.ScaleType.CENTER_CROP);
        fragmentArrayList.add(current);

        current = new HeaderFragment();
        current.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        fragmentArrayList.add(current);

        current = new HeaderFragment();
        current.setScaleType(ImageView.ScaleType.FIT_CENTER);
        fragmentArrayList.add(current);

        current = new HeaderFragment();
        current.setScaleType(ImageView.ScaleType.FIT_END);
        fragmentArrayList.add(current);

        current = new HeaderFragment();
        current.setScaleType(ImageView.ScaleType.FIT_START);
        fragmentArrayList.add(current);

        current = new HeaderFragment();
        current.setScaleType(ImageView.ScaleType.FIT_XY);
        fragmentArrayList.add(current);

        current = new HeaderFragment();
        current.setScaleType(ImageView.ScaleType.MATRIX);
        fragmentArrayList.add(current);


        //ImageView imgView;
        //imgView =(ImageView) current.getView().findViewById(R.id.fragment_header_image_view);
        //imgView.setScaleType(ImageView.ScaleType.MATRIX);


        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this.getSupportFragmentManager());
        viewPagerAdapter.setContent(fragmentArrayList);
        viewPager.setAdapter(viewPagerAdapter);

        ListViewAdapter listViewAdapter = new ListViewAdapter(this,listResult);
        listView.addHeaderView(listViewHeader);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;

        AbsListView.LayoutParams headerViewParams = new AbsListView.LayoutParams(width,300);
        listViewHeader.setLayoutParams(headerViewParams);

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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("message3","Dialog");
        setResult(RESULT_OK,intent);
        super.onBackPressed();
    }
}
