package com.example.smartbulter.utils;

import android.util.Log;

/**
 * 项目名：  SmartBulter
 * 包名：    com.example.smartbulter.ui
 * 文件名：  L
 * 创建者：  ldx
 * 创建时间：2019/1/29 21:25
 * 描述：    Log封装类
 */
public class L {

    //开关
    public static final boolean DESBUG=true;
    //TAG
    public static final String TAG="SmartBulter";
    //五个等级DIWEF

    public static void d(String text){
        if(DESBUG){
            Log.d(TAG,text);
        }
    }
    public static void i(String text){
        if(DESBUG){
            Log.i(TAG,text);
        }
    }
    public static void w(String text){
        if(DESBUG){
            Log.w(TAG,text);
        }
    }
    public static void e(String text){
        if(DESBUG){
            Log.e(TAG,text);
        }
    }

}
