package com.example.user.chendemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.user.chendemo.adapter.ViewPagerAdapter;
import com.example.user.chendemo.listViewFragments.HeaderFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by user on 2017/2/15.
 */

public class HeaderViewPager extends AppCompatActivity {

    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<Fragment>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.list_view_header); //maybe need a diff. file

        initView();
        //ButterKnife.bind(this);
    }
    public void initView(){
        viewPager = (ViewPager) findViewById(R.id.view_pager_header);
        fragmentArrayList.add(new HeaderFragment());
        fragmentArrayList.add(new HeaderFragment());
        fragmentArrayList.add(new HeaderFragment());
        fragmentArrayList.add(new HeaderFragment());
        fragmentArrayList.add(new HeaderFragment());
        fragmentArrayList.add(new HeaderFragment());
        fragmentArrayList.add(new HeaderFragment());

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this.getSupportFragmentManager());
        viewPagerAdapter.setContent(fragmentArrayList);
        viewPager.setAdapter(viewPagerAdapter);

    }
}
