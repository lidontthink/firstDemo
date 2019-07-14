package com.example.smartbulter.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartbulter.R;
import com.example.smartbulter.entity.MyUser;
import com.example.smartbulter.ui.CourierActivity;
import com.example.smartbulter.ui.LoginActivity;
import com.example.smartbulter.ui.PhoneActivity;
import com.example.smartbulter.utils.L;
import com.example.smartbulter.utils.UtilTools;
import com.example.smartbulter.view.CustomDialog;

import java.io.File;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 服务管家
 */
public class UserFragment extends Fragment implements View.OnClickListener {
    private Button btn_exit_user;
    private TextView edit_user;
    private EditText et_username,et_sex,et_age,et_desc;
    //更新按钮
    private Button btn_update_ok;
    //圆形图像
    private CircleImageView profile_image;
    private CustomDialog dialog;

    private Button btn_camera;
    private Button btn_picture;
    private Button btn_cancel;

    //快递查询
    private TextView tv_courier;
    //归属地查询
    private TextView tv_phone;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_user,null);

        //初始化View
        findView(view);
        return view;
    }
    //初始化View
    private void findView(View view) {
        btn_exit_user=view.findViewById(R.id.btn_exit_user);
        btn_exit_user.setOnClickListener(this);

        edit_user= view.findViewById(R.id.edit_user);
        edit_user.setOnClickListener(this);

        //快递，归属地
        tv_courier=view.findViewById(R.id.tv_courier);
        tv_courier.setOnClickListener(this);
        tv_phone=view.findViewById(R.id.tv_phone);
        tv_phone.setOnClickListener(this);


        et_username=view.findViewById(R.id.et_username);
        et_sex=view.findViewById(R.id.et_sex);
        et_age=view.findViewById(R.id.et_age);
        et_desc=view.findViewById(R.id.et_desc);

        btn_update_ok=view.findViewById(R.id.btn_update_ok);
        btn_update_ok.setOnClickListener(this);

        profile_image=view.findViewById(R.id.profile_image);
        profile_image.setOnClickListener(this);

        //获取缓存的图片
        UtilTools.getImageToShare(getActivity(),profile_image);

        //调用dialog
        dialog=new CustomDialog(getActivity(),0,0,R.layout.dialog_photo,R.style.pop_anim_style, Gravity.BOTTOM,0);
        //屏幕外点击无效
        dialog.setCancelable(false);

        btn_camera=(Button)dialog.findViewById(R.id.btn_camera);
        btn_camera.setOnClickListener(this);
        btn_picture=(Button)dialog.findViewById(R.id.btn_picture);
        btn_picture.setOnClickListener(this);
        btn_cancel=(Button)dialog.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);

        //默认不可点击
        setEnabled(false);

        //设置具体的值
        final MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
        et_username.setText(userInfo.getUsername());
        et_sex.setText(userInfo.isSex()?"男":"女");
        et_age.setText(userInfo.getAge()+"");
        et_desc.setText(userInfo.getDesc());


    }
    //控制焦点
    private void setEnabled (boolean is){
        et_username.setEnabled(is);
        et_sex.setEnabled(is);
        et_age.setEnabled(is);
        et_desc.setEnabled(is);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_exit_user:
                //退出登录
                //清除缓存对象
                MyUser.logOut();
                //现在的currentUser是null的
                BmobUser currentUser=MyUser.getCurrentUser(MyUser.class);
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
            case R.id.edit_user:
                setEnabled(true);
                //编辑资料
                btn_update_ok.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_update_ok:
                //拿到输入框的值
                String username=et_username.getText().toString();
                String age=et_age.getText().toString();
                String sex=et_sex.getText().toString();
                String desc=et_desc.getText().toString();

                //判断是否为空
                if(!(TextUtils.isEmpty(username))&&!(TextUtils.isEmpty(age))&&!(TextUtils.isEmpty(sex))){
                    //更新属性
                    MyUser user=new MyUser();
                    user.setUsername(username);
                    user.setAge(Integer.parseInt(age));
                    //性别
                    if(sex.equals("男")){
                        user.setSex(true);
                    }else {
                        user.setSex(false);
                    }
                    //简介
                    if(!TextUtils.isEmpty(desc)){
                        user.setDesc(desc);
                    }else{
                        user.setDesc("这个人很懒，什么也没有留下！");
                    }
                    //调用更新方法
                    BmobUser bmobUser=BmobUser.getCurrentUser(MyUser.class);
                    bmobUser.update(bmobUser.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                //修改成功
                                setEnabled(false);
                                btn_update_ok.setVisibility(View.VISIBLE);
                                Toast.makeText(getActivity(),"修改成功",Toast.LENGTH_SHORT).show();

                            }else {
                                Toast.makeText(getActivity(),"修改失败",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else {
                    Toast.makeText(getActivity(),"输入框不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.profile_image:
                dialog.show();
                break;
            case R.id.btn_cancel:
                dialog.dismiss();
                break;
            case R.id.btn_camera:
                toCamera();
                break;
            case R.id.btn_picture:
                toPicture();
                break;
            case R.id.tv_courier:
                startActivity(new Intent(getActivity(), CourierActivity.class));
                break;
            case R.id.tv_phone:
                startActivity(new Intent(getActivity(), PhoneActivity.class));
                break;
        }
    }

    public static final String PHOTO_IMAGE_FILE_NAME="fileImg.jpg";
    public static final int CAMERA_REQUEST_CODE=100;
    public static final int IMAGE_REQUEST_CODE=101;
    private File tempFile=null;
    public static final int RESULT_REQUEST_CODE=102;

    //跳转相机
    private void toCamera() {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断内存卡是否可用，可用的化就进行存储
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                PHOTO_IMAGE_FILE_NAME)));
        startActivityForResult(intent,CAMERA_REQUEST_CODE);
        dialog.dismiss();
    }

    //跳转相册
    private void toPicture() {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_REQUEST_CODE);
        dialog.dismiss();
    }

    //相机相册返回的返回码
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode!=getActivity().RESULT_CANCELED){
            switch (requestCode){
                //相册数据
                case IMAGE_REQUEST_CODE:
                    startPhotoZoom(data.getData());
                    break;
                //相机数据
                case CAMERA_REQUEST_CODE:
                    tempFile=new File(Environment.getExternalStorageDirectory(),PHOTO_IMAGE_FILE_NAME);
                    startPhotoZoom(Uri.fromFile(tempFile));
                    break;
                case RESULT_REQUEST_CODE:
                    //有可能点击舍弃
                    if (data != null) {
                        //拿到图片设置
                        setImageToView(data);
                        //既然已经设置了图片，我们原先的就应该删除
                        if (tempFile != null) {
                            tempFile.delete();
                        }
                    }
                    break;
            }
        }

    }
    //裁剪
    private void startPhotoZoom(Uri uri){
        if(uri==null){
            L.e("uri==null");
            return;
        }
        Intent intent=new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"image/*");
        //设置裁剪
        intent.putExtra("crop","true");
        //裁剪框高比例
        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);
        //裁剪图片的质量
        intent.putExtra("outputX",320);
        intent.putExtra("outputY",320);
        //发送数据
        intent.putExtra("return-data", true);
        startActivityForResult(intent,RESULT_REQUEST_CODE);
    }

    //设置图片
    private void setImageToView(Intent data) {
        Bundle bundle = data.getExtras();
        if (bundle != null) {
            Bitmap bitmap = bundle.getParcelable("data");
            profile_image.setImageBitmap(bitmap);
        }
    }
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        dialog.dismiss();//如果你没写这段代码，就会报异常
//        getActivity().finish();
//
//
//
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //保存
        UtilTools.putImageToShare(getActivity(),profile_image);
    }
}
