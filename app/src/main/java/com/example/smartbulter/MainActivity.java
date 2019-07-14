package com.example.smartbulter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.smartbulter.fragment.ButlerFragment;
import com.example.smartbulter.fragment.GirlFragment;
import com.example.smartbulter.fragment.UserFragment;
import com.example.smartbulter.fragment.WeChatFragment;
import com.example.smartbulter.ui.SettingActivity;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //TabLayot
    private TabLayout mTabLayout;
    //ViewPager
    private ViewPager mViewPager;
    //Title
    private List<String> mTitle;
    //Fragment
    private List<Fragment> mFragment;
    //悬浮窗
    private FloatingActionButton fab_setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //去掉阴影
        getSupportActionBar().setElevation(0);

        initData();
        initView();



    }

    //初始化数据
    private void initData() {
        mTitle=new ArrayList<String>();
        mTitle.add("服务管家");
        mTitle.add("微信精选");
        mTitle.add("美女社区");
        mTitle.add("个人中心");

        mFragment=new ArrayList<Fragment>();
        mFragment.add(new ButlerFragment());
        mFragment.add(new WeChatFragment());
        mFragment.add(new GirlFragment());
        mFragment.add(new UserFragment());



    }
    //初始化View
    private void initView() {
        //初始化悬浮窗
        fab_setting=findViewById(R.id.fab_setting);
        fab_setting.setOnClickListener(this);
        //默认为隐藏状态
        fab_setting.setVisibility(View.GONE);
        //初始化容器
        mTabLayout=findViewById(R.id.mTabLayout);
        mViewPager=findViewById(R.id.mViewPage);

        //预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());

        //mViewPager的滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("TAG","position:"+position);
                if(position==0){
                    fab_setting.setVisibility(View.GONE);
                }else {
                    fab_setting.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //设置适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            //选中的item
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }
            //选择的item数量
            @Override
            public int getCount() {
                return mFragment.size();
            }

            //设置标题

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });
        //绑定：mTabLayout和mViewpage绑定一起
        mTabLayout.setupWithViewPager(mViewPager);



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;
        }
    }
}
