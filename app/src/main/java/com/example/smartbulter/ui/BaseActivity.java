package com.example.smartbulter.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * 项目名：  SmartBulter
 * 包名：    com.example.smartbulter.ui
 * 文件名：  BaseActivity
 * 创建者：  ldx
 * 创建时间：2019/1/24 20:52
 * 描述：    Activity的基类
 */

/**
 * 主要做的事情
 * 1.统一的属性
 * 2.统一的接口
 * 3.统一的方法
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //显示返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    //菜单栏的操作：返回键

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
