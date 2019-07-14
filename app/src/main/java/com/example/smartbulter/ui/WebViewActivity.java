package com.example.smartbulter.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.smartbulter.R;
import com.example.smartbulter.utils.L;

/**
 * 项目名：  SmartBulter
 * 包名：    com.example.smartbulter.ui
 * 文件名：  WebViewActivity
 * 创建者：  ldx 李东新
 * 创建时间：2019/3/5 15:35
 * 描述：    新闻详情
 */
public class WebViewActivity extends BaseActivity {

    //进度
    private ProgressBar mProgressBar;
    //网页
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        //初始化View
        initView();
    }

    //初始化View
    private void initView() {

        mProgressBar=findViewById(R.id.mProgressBar);
        mWebView=findViewById(R.id.mWebView);

        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        final String url=intent.getStringExtra("url");
        L.i("url:"+url);

        //设置标题
        getSupportActionBar().setTitle(title);

        //进行加载网页的逻辑

        //支持JS
        mWebView.getSettings().setJavaScriptEnabled(true);
        //支持缩放
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        //接口回调
        mWebView.setWebChromeClient(new WebViewClient());
        //加载网页
        mWebView.loadUrl(url);

        //本地显示
        mWebView.setWebViewClient(new android.webkit.WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return true;
            }
        });

    }

    public class WebViewClient extends WebChromeClient{

        //进度条变化的监听
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if(newProgress==100){
                mProgressBar.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, newProgress);
        }
    }
}
