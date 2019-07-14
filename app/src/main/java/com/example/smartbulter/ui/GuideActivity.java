package com.example.smartbulter.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.smartbulter.MainActivity;
import com.example.smartbulter.R;
import com.example.smartbulter.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导页
 */


public class GuideActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mViewPage;
    //容器
    private List<View> mList=new ArrayList<>();
    //控件View
    private View view1,view2,view3;
    //小圆点
    private ImageView point1,point2,point3;
    //跳过
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();
    }

    private void initView(){
        iv_back=findViewById(R.id.iv_back);
        mViewPage=(ViewPager)findViewById(R.id.mViewPage);
        point1=findViewById(R.id.point1);
        point2=findViewById(R.id.point2);
        point3=findViewById(R.id.point3);

        //设置默认图片
        setPointImg(true,false,false);

        view1=View.inflate(this,R.layout.pager_item_one,null);
        view2=View.inflate(this,R.layout.pager_item_two,null);
        view3=View.inflate(this,R.layout.pager_item_three,null);
        view3.findViewById(R.id.btn_start).setOnClickListener((View.OnClickListener) this);
        iv_back.findViewById(R.id.iv_back).setOnClickListener(this);



        mList.add(view1);
        mList.add(view2);
        mList.add(view3);

        mViewPage.setAdapter(new GuideAdapter());
        //设置ViewPage
        mViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            //设置被选中时的状态
            @Override
            public void onPageSelected(int position) {
                L.i("positon:"+position);
                switch (position){
                    case 0:
                        setPointImg(true,false,false);
                        iv_back.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        setPointImg(false,true,false);
                        iv_back.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        setPointImg(false,false,true);
                        iv_back.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
            case R.id.iv_back:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }

    class GuideAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        //选中增加的页面
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ((ViewPager)container).addView(mList.get(position));
            return mList.get(position);
        }

        //划过删除的页面

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            ((ViewPager)container).removeView(mList.get(position));
//            super.destroyItem(container, position, object);
        }
    }

        //为小圆点设置选中效果
        private void setPointImg(boolean isCheck1,boolean isCheck2,boolean isCheck3){
            if(isCheck1){
                point1.setBackgroundResource(R.drawable.point_on);
            }else {
                point1.setBackgroundResource(R.drawable.point_off);
            }

            if(isCheck2){
                point2.setBackgroundResource(R.drawable.point_on);
            }else {
                point2.setBackgroundResource(R.drawable.point_off);
            }

            if(isCheck3){
                point3.setBackgroundResource(R.drawable.point_on);
            }else {
                point3.setBackgroundResource(R.drawable.point_off);
            }
        }
}
