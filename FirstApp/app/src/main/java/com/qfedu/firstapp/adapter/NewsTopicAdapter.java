package com.qfedu.firstapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qfedu.firstapp.R;
import com.qfedu.firstapp.bean.NewsTopic;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsTopicAdapter extends BaseAdapter {
    private Context context;
    private List<NewsTopic> topicList;

    public NewsTopicAdapter(Context context, List<NewsTopic> list) {
        this.context = context;
        this.topicList = list;
    }

    /**
     * 将分页请求回来的list数据，灌入整个数据集合容器中
     * @param list
     */
    public void addNewTopicList(List<NewsTopic> list){
        topicList.addAll(list);
    }

    @Override
    public int getCount() {
        return topicList.size();
    }

    @Override
    public NewsTopic getItem(int position) {
        return topicList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //1.要展示的数据
        NewsTopic topic = topicList.get(i);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_newstopic, viewGroup, false);
        }
        TextView title = view.findViewById(R.id.tv_topic_title);
        ImageView cover1 = view.findViewById(R.id.cover_1);
        ImageView cover2 = view.findViewById(R.id.cover_2);
        ImageView cover3 = view.findViewById(R.id.cover_3);

        //为布局控件设置内容值
        title.setText(topic.getTitle());
        if(!"".equals(topic.getThumbnail_pic_s())){
            Picasso.with(context).load(topic.getThumbnail_pic_s()).into(cover1);
        }
        if(!"".equals(topic.getThumbnail_pic_s02())){
            Picasso.with(context).load(topic.getThumbnail_pic_s02()).into(cover2);
        }
        if(!"".equals(topic.getThumbnail_pic_s03())){
            Picasso.with(context).load(topic.getThumbnail_pic_s03()).into(cover3);
        }
        return view;
    }
}
