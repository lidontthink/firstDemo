package com.example.smartbulter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartbulter.R;
import com.example.smartbulter.entity.WeChatData;
import com.example.smartbulter.utils.L;
import com.example.smartbulter.utils.PicassoUtils;

import java.util.List;

/**
 * 项目名：  SmartBulter
 * 包名：    com.example.smartbulter.adapter
 * 文件名：  WeChatAdapter
 * 创建者：  ldx 李东新
 * 创建时间：2019/3/4 22:02
 * 描述：    微信精选Adapter
 */
public class WeChatAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<WeChatData> mlist;
    private WeChatData data;

    private int width,height;
    private WindowManager wm;

    public WeChatAdapter(Context mContext, List<WeChatData> mlist){
        this.mContext=mContext;
        this.mlist=mlist;
        inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        wm= (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        width=wm.getDefaultDisplay().getWidth();
        height=wm.getDefaultDisplay().getHeight();
        L.i("width"+width+"height"+height);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(viewHolder==null){
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.wechat_item,null);
            viewHolder.iv_img=convertView.findViewById(R.id.iv_img);
            viewHolder.tv_title=convertView.findViewById(R.id.tv_title);
            viewHolder.tv_source=convertView.findViewById(R.id.tv_source);
            convertView.setTag(viewHolder);
        }else{
            convertView.getTag();
        }
        data=mlist.get(position);
        viewHolder.tv_title.setText(data.getTitle());
        viewHolder.tv_source.setText(data.getSource());

        //加载图片
        //Picasso.with(mContext).load(data.getImgurl()).into(viewHolder.iv_img );
        //PicassoUtils.loadImageView(mContext,data.getImgurl(),viewHolder.iv_img);
        //PicassoUtils.loadImageViewSize(mContext,data.getImgurl(),width/3,200,viewHolder.iv_img);


        return convertView;
    }

    class ViewHolder{
        private ImageView iv_img;
        private TextView tv_title;
        private TextView tv_source;
    }
}
