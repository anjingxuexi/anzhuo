package com.qfedu.firstapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qfedu.firstapp.R;
import com.qfedu.firstapp.bean.HotVideo;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class HotVideoAdapter extends BaseAdapter {

    private Context context;
    private List<HotVideo> hotVideoList;

    public HotVideoAdapter() {
    }

    public HotVideoAdapter(Context context, List<HotVideo> hotVideoList) {
        this.context = context;
        this.hotVideoList = hotVideoList;
    }

    @Override
    public int getCount() {
        return hotVideoList.size();
    }

    @Override
    public HotVideo getItem(int i) {
        return hotVideoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        //1、确定要展示的数据
        HotVideo video = hotVideoList.get(position);

        //2、准备一个数据展示的布局模板（视图模板）
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_hotvideo, viewGroup, false);
        }

//        TextView title = itemRoot.findViewById(R.id.tv_video_title);
        ImageView imgCover = view.findViewById(R.id.img_cover);
//        TextView play = itemRoot.findViewById(R.id.tv_play);
        TextView digg = view.findViewById(R.id.tv_digg);
//        TextView comm = itemRoot.findViewById(R.id.tv_comm);

        //3、把数据设置到布局模板上的控件上进行展示
//        title.setText(video.getTitle());

        //热门视频的封面图片设置
        Picasso.with(context).load(video.getItem_cover()).into(imgCover);

//        play.setText(video.getPlay_count()+"");
        long diggCount = video.getDigg_count();//点赞数
        if (diggCount < 10000) {//10000赞以内，直接展示具体值
            digg.setText(diggCount + "");
        } else if (diggCount >= 10000) {
            //以万为单位进行展示输出
            Double dou = diggCount / 10000d;

            DecimalFormat format = new DecimalFormat("0.0");
            String result = format.format(dou);
            digg.setText(result + " 万");
        }
//        comm.setText(video.getComment_count()+"");

        return view;
    }
}
