package com.example.smartbulter.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartbulter.MainActivity;
import com.example.smartbulter.R;
import com.example.smartbulter.entity.MyUser;
import com.example.smartbulter.utils.ShareUtils;
import com.example.smartbulter.view.CustomDialog;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 登录界面
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_registered;
    private EditText et_name;
    private EditText et_password;
    private Button btn_login;
    private CustomDialog dialog;

    private CheckBox keep_password;
    private TextView tv_forget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化界面
        initView();
    }

    private void initView() {
        btn_registered=findViewById(R.id.btn_registered);
        //注册
        btn_registered.setOnClickListener(this);
        et_name=findViewById(R.id.et_name);
        et_password=findViewById(R.id.et_password);
        btn_login=findViewById(R.id.btn_login);
        //登录
        btn_login.setOnClickListener(this);

        keep_password = (CheckBox) findViewById(R.id.keep_password);
        boolean isKeep = ShareUtils.getBoolean(this, "keeppass", false);
        keep_password.setChecked(isKeep);
        if(isKeep){
            String name = ShareUtils.getString(this, "name", "");
            String password = ShareUtils.getString(this, "password", "");
            et_name.setText(name);
            et_password.setText(password);
        }

        tv_forget = (TextView) findViewById(R.id.tv_forget);
        tv_forget.setOnClickListener(this);

        //调用dialog
        dialog=new CustomDialog(this,100,100,R.layout.dialog_loding,R.style.Theme_dialog, Gravity.CENTER,R.style.pop_anim_style);
        //屏幕外点击无效
        dialog.setCancelable(false);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_registered:
                startActivity(new Intent(this,RegisteredActivity.class));
                break;
            case R.id.tv_forget:
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;
            case R.id.btn_login:
                //获取输入框的值
                String name=et_name.getText().toString().trim();
                String password=et_password.getText().toString().trim();
                //判断输入框的值是否为空
                if((!TextUtils.isEmpty(name))&&(!TextUtils.isEmpty(password))){
                    //有内容展示dialog
                    dialog.show();
                    //登录
                    final MyUser user=new MyUser();
                    user.setUsername(name);
                    user.setPassword(password);
                    user.login(new SaveListener<MyUser>() {

                        @Override
                        public void done(MyUser myUser, BmobException e) {
                            //有结果则隐藏dialog
                            dialog.dismiss();
                            //判断结果
                            if(e==null){
                                //判断邮箱是否验证
                                if(user.getEmailVerified()){
                                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                    finish();
                                }else{
                                    Toast.makeText(LoginActivity.this,"请前往邮箱进行验证",Toast.LENGTH_SHORT).show();
                                }

                            }else{
                                Toast.makeText(LoginActivity.this,"登录失败"+e.toString(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(this,"输入框不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //假设我现在输入用户名和密码，但是我不点击登录，而是直接退出了
    @Override
    protected void onDestroy() {
        super.onDestroy();

        //保存状态
        ShareUtils.putBoolean(this, "keeppass", keep_password.isChecked());

        //是否记住密码
        if (keep_password.isChecked()) {
            //记住用户名和密码
            ShareUtils.putString(this, "name", et_name.getText().toString().trim());
            ShareUtils.putString(this, "password", et_password.getText().toString().trim());
        } else {
            ShareUtils.deleShare(this, "name");
            ShareUtils.deleShare(this, "password");
        }
    }
}
