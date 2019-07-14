package com.example.smartbulter.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.smartbulter.MainActivity;
import com.example.smartbulter.R;
import com.example.smartbulter.utils.ShareUtils;
import com.example.smartbulter.utils.StaticClass;
import com.example.smartbulter.utils.UtilTools;

public class SplashActivity extends AppCompatActivity {

    /**
     * 1.延时2000；
     * 2.判断程序是否第一次运行
     * 3.自定义字体
     * 4.Activity全屏主题
     */
    private TextView tv_splash;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case StaticClass.HANDLER_SPLASH:
                    //判断程序是否第一次运行
                    if(isFirst()){
                        startActivity(new Intent(SplashActivity.this,GuideActivity.class));

                    }else{
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }
    //初始化View
    private void initView() {
        //延时2000
        handler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH,2000);
        tv_splash=findViewById(R.id.tv_splash);
        //设置字体
        UtilTools.setFont(this,tv_splash);
    }

    //判断程序是否第一次运行
    public boolean isFirst(){
        boolean isFirst= ShareUtils.getBoolean(this,StaticClass.SHARE_IS_FIRST,true);
        if(isFirst){
            //标记我们已经启动过App
            ShareUtils.putBoolean(this,StaticClass.SHARE_IS_FIRST,false);
            return true;
        }else {
            return false;
        }
    }
}
