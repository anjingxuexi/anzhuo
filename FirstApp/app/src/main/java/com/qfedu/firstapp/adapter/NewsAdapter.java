package com.qfedu.firstapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qfedu.firstapp.bean.NewsBean;
import com.qfedu.firstapp.R;

import java.util.List;

public class NewsAdapter extends BaseAdapter {
    private List<NewsBean> newList;
    private Context context;

    public NewsAdapter(Context context, List<NewsBean> list) {
        this.context = context;
        this.newList = list;
    }

    /**
     * 该方法用于返回 一共要展示多少条数据，即数据条目的个数
     *
     * @return 要展示的数据条目个数
     */
    @Override
    public int getCount() {
        return newList.size();
    }

    /**
     * 该方法用于在展示第position个条目时，得到该条目要展示的数据
     *
     * @param position 条目的下标
     * @return 要展示的该条目的数据
     */
    @Override
    public NewsBean getItem(int position) {
        return newList.get(position);
    }

    /**
     * 每一个条目要保持唯一的编号
     *
     * @param position 条目的下标
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 该方法用于得到ListView当中要展示的每一个条目的视图
     *
     * @param position  要展示的当前条目的下标，从0开始
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        //1、确定要展示的条目所要展示的具体数据
        NewsBean newsBean = newList.get(position);

        //2、提供一个摆放数据的视图模板（布局样式）
        //2.1 先拿到布局文件
        //LayoutInflater: 泵 水泵...提供动力
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_newlist, viewGroup, false);
        }
        //2.2 拿到布局文件中的控件
        TextView tvTitle = view.findViewById(R.id.tv_title);
        TextView tvDesc = view.findViewById(R.id.tv_desc);
        TextView tvOriginal = view.findViewById(R.id.tv_original);
        TextView tvRead = view.findViewById(R.id.tv_read);
        TextView tvComment = view.findViewById(R.id.tv_comment);
        TextView tvShare = view.findViewById(R.id.tv_share);
        //3、把数据设置到模板的控件上
        tvTitle.setText(newsBean.getTitle());
        tvDesc.setText(newsBean.getDescription());
        tvOriginal.setText(newsBean.getOriginal());
        tvRead.setText(newsBean.getRead() + "阅读");
        tvComment.setText(newsBean.getComment() + "评论");
        tvShare.setText(newsBean.getShare() + "分享");

        return view;
    }
}
