package com.example.smartbulter.entity;

import cn.bmob.v3.BmobUser;

/**
 * 项目名：  SmartBulter
 * 包名：    com.example.smartbulter.entity
 * 文件名：  MyUser
 * 创建者：  ldx 李东新
 * 创建时间：2019/2/9 16:03
 * 描述：    用户属性
 */
public class MyUser extends BmobUser {
    //年龄
    private int age;
    //性别
    private boolean sex;
    //简介
    private String desc;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
