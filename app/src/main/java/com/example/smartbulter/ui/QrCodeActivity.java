package com.example.smartbulter.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.smartbulter.R;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class QrCodeActivity extends AppCompatActivity {

    //我的二维码
    private ImageView iv_qr_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        initView();
    }

    private void initView() {

        iv_qr_code = (ImageView) findViewById(R.id.iv_qr_code);
        //屏幕的宽
        int width = getResources().getDisplayMetrics().widthPixels;

        Bitmap qrCodeBitmap = EncodingUtils.createQRCode("练习Demo", width / 2, width / 2,
                BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        iv_qr_code.setImageBitmap(qrCodeBitmap);
    }
}