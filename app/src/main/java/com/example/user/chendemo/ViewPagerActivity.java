package com.example.user.chendemo;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import com.example.user.chendemo.adapter.ViewPagerAdapter;
import com.example.user.chendemo.bean.Book;
import com.example.user.chendemo.fragment.ContentFragment;
import com.example.user.chendemo.fragment.HistoryFragment;
import com.example.user.chendemo.fragment.LoginFragment;
import com.example.user.chendemo.util.UtilLog;

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
        Intent intent=getIntent();
//        String msg = intent.getStringExtra("key");
//        UtilLog.logD("ViewPagerActivity, value is " ,msg);
//        Bundle bundle = intent.getExtras();
//        Integer number = bundle.getInt("Integer",0);
//        int fakeNumber = bundle.getInt("fake",0);
//        UtilLog.logD("ViewPagerActivity, number is ", number.toString());
//        UtilLog.logD("ViewPagerActivity, fake number  is " ,String.valueOf(fakeNumber));
//        Book book = (Book) bundle.getSerializable("book");
//        UtilLog.logD("ViewPagerActivity, author  is ", book.getAuthor());
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("message","ViewPager");
        setResult(RESULT_OK,intent);
        super.onBackPressed();          //has to be last line
    }
}
