package com.qfedu.firstapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.qfedu.firstapp.R;
import com.qfedu.firstapp.adapter.GuideAdapter;

/**
 * 该activity是app的引导界面，用于展示引导页
 */
public class GuideActivity extends AppCompatActivity {
    private ViewPager vpGuide;

    //分析：使用什么技术 才能实现 多张图片可以左右滑动，既可以左滑，也可以右滑
    //ViewPager：android当中一个视图控件，该控件允许展示多个视图，多个视图会依次进行展示
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        vpGuide = findViewById(R.id.vp_guide);
        //adapter:适配器  viewpager和列表控件类似，在展示内容时，也需要一个适配器
        //ListView: 适配器  MyClass extend BaseAdapter
        //ViewPager: 适配器 MyClass extend PagerAdapter
        vpGuide.setAdapter(new GuideAdapter(this));
    }
}