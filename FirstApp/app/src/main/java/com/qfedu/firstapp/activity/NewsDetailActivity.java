package com.qfedu.firstapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.qfedu.firstapp.bean.NewsBean;
import com.qfedu.firstapp.R;

public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        Intent intent = getIntent();
        NewsBean newsBean = (NewsBean) intent.getSerializableExtra("news");
        System.out.println(newsBean.getTitle());
        System.out.println(newsBean.getDescription());
    }
}