package com.example.smartbulter.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.smartbulter.R;

/**
 * 项目名：  SmartBulter
 * 包名：    com.example.smartbulter.view
 * 文件名：  CustomDialog
 * 创建者：  ldx 李东新
 * 创建时间：2019/2/18 10:12
 * 描述：    TODO
 */
public class CustomDialog extends Dialog {

    //定义模板
    public CustomDialog(Context context,int layout,int style) {
        this(context, WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT,layout,style, Gravity.CENTER);
    }

    //定义属性
    public CustomDialog(Context context,int width,int height,int layout,int style,int gravity,int anim){
        super(context,style);
        //设置属性
        setContentView(layout);
        Window window=getWindow();
        WindowManager.LayoutParams layoutParams=window.getAttributes();
        layoutParams.width=WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height=WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity=gravity;
        window.setAttributes(layoutParams);
        window.setWindowAnimations(anim);
    }
    //实例
    public CustomDialog(Context context,int width,int height,int layout,int style,int gravity){
        this(context,width,height,layout,style,gravity, R.style.pop_anim_style);
    }
}
