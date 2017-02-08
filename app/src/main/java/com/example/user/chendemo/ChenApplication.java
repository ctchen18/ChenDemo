package com.example.user.chendemo;

import android.app.Application;

import com.example.user.chendemo.util.UtilLog;

/**
 * Created by user on 2017/2/6.
 */

public class ChenApplication extends Application {

    //Set switch in the log
    @Override
    public void onCreate(){
        super.onCreate();
        //setting deubg to true
        UtilLog.setDebug(true);

    }
}
