package com.example.smartbulter.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 项目名：  SmartBulter
 * 包名：    com.example.smartbulter.utils
 * 文件名：  ShareUtils
 * 创建者：  ldx
 * 创建时间：2019/1/29 22:05
 * 描述：    SharedPreference封装
 */
public class ShareUtils {
    public static final String NAME="config";
    //键 值
    public static final void putString(Context mContext,String key,String value){
        SharedPreferences sp=mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }
    //键 默认值
    public static final String getString(Context mContext,String key,String defValue){
        SharedPreferences sp=mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getString(key,defValue);
    }
    //键 值
    public static final void putInt(Context mContext,String key,int value){
        SharedPreferences sp=mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putInt(key,value).commit();
    }
    //键 默认值
    public static final int getInt(Context mContext,String key,int defValue){
        SharedPreferences sp=mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getInt(key,defValue);
    }
    //键 值
    public static final void putBoolean(Context mContext,String key,Boolean value){
        SharedPreferences sp=mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }
    //键 默认值
    public static final boolean getBoolean(Context mContext,String key,Boolean defValue){
        SharedPreferences sp=mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getBoolean(key,defValue);
    }

    //删除单个
    public static void deleShare(Context mContext,String key){
        SharedPreferences sp=mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }

    //删除 全部
    public static void deleAll(Context mContext,String key){
        SharedPreferences sp=mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }
}
