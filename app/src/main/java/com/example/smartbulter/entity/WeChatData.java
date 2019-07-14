package com.example.smartbulter.entity;

/**
 * 项目名：  SmartBulter
 * 包名：    com.example.smartbulter.entity
 * 文件名：  WeChatData
 * 创建者：  ldx 李东新
 * 创建时间：2019/3/4 22:05
 * 描述：    微信精选的实体类
 */
public class WeChatData {

    //标题
    private String title;
    //出处
    private String source;
    //图片的url
    private String imgUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
