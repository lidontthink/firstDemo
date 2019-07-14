package com.example.smartbulter.entity;

/**
 * 项目名：  SmartBulter
 * 包名：    com.example.smartbulter.entity
 * 文件名：  ChatListData
 * 创建者：  ldx 李东新
 * 创建时间：2019/2/28 18:03
 * 描述：    对话列表的实体
 */
public class ChatListData {

    //区分左边还是右边的Type
    private int type;
    //文本
    private String text;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
