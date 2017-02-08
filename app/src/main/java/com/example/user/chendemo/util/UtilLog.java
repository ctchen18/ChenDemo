package com.example.user.chendemo.util;

import android.util.Log;

/**
 * Created by user on 2017/2/6.
 */

public class UtilLog {
    //make this false before publishing the application
    private static boolean debug =false;

    public static void setDebug(boolean x){
        debug = x;
    }

    public static void logD(String key, String value){
        if(debug){
            Log.d(key,value);
        }
    }
    //TODO create logD for debug, e for error, f, g, and so on

    public static void lodE(String key, String value){
        if(debug){
            Log.e(key,value);
        }
    }

    public static void lodI(String key, String value){
        if(debug){
            Log.i(key,value);
        }
    }

    public static void lodV(String key, String value){
        if(debug){
            Log.v(key,value);
        }
    }

    public static void lodW(String key, String value){
        if(debug){
            Log.w(key,value);
        }
    }
}
