package com.example.user.chendemo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import com.example.user.chendemo.adapter.ViewPagerAdapter;
import com.example.user.chendemo.fragment.ContentFragment;
import com.example.user.chendemo.fragment.HistoryFragment;
import com.example.user.chendemo.fragment.LoginFragment;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity {

    //use .v4 version
    //3 fragments are needed for 3 pages
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        initView();
    }

    public void initView(){
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        fragmentList.add(new HistoryFragment());
        fragmentList.add(new ContentFragment());
        fragmentList.add(new LoginFragment());
        ViewPagerAdapter viewPagerAdapter= new ViewPagerAdapter(this.getSupportFragmentManager()); //control init / destroy, no view holder
        viewPagerAdapter.setContent(fragmentList);
        viewPager.setAdapter((viewPagerAdapter));

        tabLayout=(TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }
}
