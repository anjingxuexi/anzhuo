package com.qfedu.firstapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.qfedu.firstapp.adapter.NewsAdapter;
import com.qfedu.firstapp.bean.NewsBean;
import com.qfedu.firstapp.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    private ListView lvNews;
    private List<NewsBean> newList = new ArrayList<NewsBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        //获取到列表控件
        lvNews = findViewById(R.id.lv_news);

        //1、准备数据
        NewsBean new1 = new NewsBean();
        new1.setTitle("河北省卫健委最新通知");
        new1.setDescription("各市卫健委（局）、中医局，雄安新区管委会...");
        new1.setOriginal("长城网");
        new1.setRead(1000);
        new1.setComment(500);
        new1.setShare(100);

        NewsBean new2 = new NewsBean();
        new2.setTitle("中国版的ChatGPT来了？");
        new2.setDescription("3月23日百度科技公司在北京召开产品发布会，会上百度CEO李彦宏发布了自家的文心一言产品...");
        new2.setOriginal("凤凰网");
        new2.setRead(10000);
        new2.setComment(9000);
        new2.setShare(200);

        NewsBean new3 = new NewsBean();
        new3.setTitle("全国两会在北京召开");
        new3.setDescription("新华社 3月5日电    全国人民代表大会第二十届第一次会议于3月5日在北京召开... ....");
        new3.setOriginal("新华社");
        new3.setComment(2000);
        new3.setShare(500);
        new3.setRead(1000);

        NewsBean new4 = new NewsBean();
        new4.setTitle("北京遭受近年来最严重的沙尘天气");
        new4.setDescription("昨天到今天，北京、河北、天津等华北地区遭受了23年第一场最大范围的沙尘天气...");
        new4.setOriginal("人民日报");
        new4.setComment(1234);
        new4.setShare(101);
        new4.setRead(20000);

        NewsBean new5 = new NewsBean();
        new5.setTitle("河北省卫健委最新通知最新通知最新通知");
        new5.setDescription("各市卫健委（局）、中医局，雄安新区管委会...");
        new5.setOriginal("长城网");
        new5.setRead(1000);
        new5.setComment(500);
        new5.setShare(100);

        NewsBean new6 = new NewsBean();
        new6.setTitle("中国版的ChatGPT来了？ChatGPT来了ChatGPT来了");
        new6.setDescription("3月23日百度科技公司在北京召开产品发布会，会上百度CEO李彦宏发布了自家的文心一言产品...");
        new6.setOriginal("凤凰网");
        new6.setRead(10000);
        new6.setComment(9000);
        new6.setShare(200);

        NewsBean new7 = new NewsBean();
        new7.setTitle("全国两会在北京召开在北京召开在北京召开");
        new7.setDescription("新华社 3月5日电    全国人民代表大会第二十届第一次会议于3月5日在北京召开... ....");
        new7.setOriginal("新华社");
        new7.setComment(2000);
        new7.setShare(500);
        new7.setRead(1000);

        NewsBean new8 = new NewsBean();
        new8.setTitle("北京遭受近年来最严重的沙尘天气沙尘天气沙尘天气沙尘天气");
        new8.setDescription("昨天到今天，北京、河北、天津等华北地区遭受了23年第一场最大范围的沙尘天气...");
        new8.setOriginal("人民日报");
        new8.setComment(1234);
        new8.setShare(101);
        new8.setRead(20000);

        //List集合,是一个容器
        newList.add(new1);
        newList.add(new2);
        newList.add(new3);
        newList.add(new4);
        newList.add(new5);
        newList.add(new6);
        newList.add(new7);
        newList.add(new8);

        //将数据设置到ListView控件中进行展示
        //设计模式：适配器模式（Adapter）
        NewsAdapter adapter = new NewsAdapter(this,newList);
        lvNews.setAdapter(adapter);

        //为ListView添加条目点击事件
        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //当用户点击ListView控件的条目时，程序会到此处执行
                openNewsDetail(position);
            }
        });
    }

    /**
     * 打开新闻详情界面，展示所点击的新闻详情
     */
    private void openNewsDetail(int position) {
        //1、先确定，点击的是哪一个条目（即条目的下标即可）

        //2、确定要查看的是哪条新闻
        NewsBean newsBean =newList.get(position);

        //3、携带着新闻数据跳转到新闻详情页面
        Intent intent = new Intent(this, NewsDetailActivity.class);
        intent.putExtra("news",newsBean);
        startActivity(intent);

    }
}