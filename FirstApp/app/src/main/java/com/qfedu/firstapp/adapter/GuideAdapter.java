package com.qfedu.firstapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.qfedu.firstapp.R;
import com.qfedu.firstapp.activity.MainActivity;

public class GuideAdapter extends PagerAdapter {
    //引导页图片的个数，此处我们设置为4
    private static final int GUIDE_NUM = 4;
    private LayoutInflater layoutInflater;
    private Context context;

    public GuideAdapter(Context context) {
        this.context = context;
        //实例化布局泵对象
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return GUIDE_NUM;//展示的视图的个数,即引导页的个数
    }

    /**
     * 当viewpager要展示每一个条目时，均会调用该方法
     *
     * @param container The containing View in which the page will be shown.
     * @param position  The page position to be instantiated.
     * @return
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View pager = layoutInflater.inflate(R.layout.item_guide, container, false);
        ImageView guideItem = pager.findViewById(R.id.img_guide);
        Button btn = pager.findViewById(R.id.btn_open);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //当用户点击 "立即体验" 按钮时 所要调用的方法
                openHome();
            }
        });
        switch (position) {//通过position参数判断，当前要展示viewpager的哪一个条目
            case 0://要展示引导页的第一张引导页
                guideItem.setImageResource(R.drawable.one_guide);//为ImageView控件设置图片资源
                btn.setVisibility(View.GONE);
                break;
            case 1://引导页第二张
                guideItem.setImageResource(R.drawable.two_guide);
                btn.setVisibility(View.GONE);
                break;
            case 2://引导页第三张
                guideItem.setImageResource(R.drawable.three_guide);
                btn.setVisibility(View.GONE);
                break;
            case 3://引导页第四张
                guideItem.setImageResource(R.drawable.four_guide);
                btn.setVisibility(View.VISIBLE);//设置为可见
                break;
            default:
                break;
        }
        container.addView(pager);
        return pager;
    }

    /**
     * 该方法用于点击"立即体验"按钮后的 打开和跳转到主界面的操作
     */
    private void openHome() {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
        //引导页面跳转到主界面的同时，要把引导页面关闭掉
        ((Activity)context).finish();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
