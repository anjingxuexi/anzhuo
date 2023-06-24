package com.qfedu.firstapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qfedu.firstapp.R;
import com.qfedu.firstapp.fragment.ContractsFragment;
import com.qfedu.firstapp.fragment.DiscoverFragment;
import com.qfedu.firstapp.fragment.IndexFragment;
import com.qfedu.firstapp.fragment.MyFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //主界面的标题控件
    private TextView tvTitle;

    //打开新页面按钮
    private TextView tvWeChat, tvContract, tvDiscover, tvMine;

    private IndexFragment indexFragment;//首页fragment
    private ContractsFragment contractsFragment;//联系人fragment
    private DiscoverFragment discoverFragment;//发现fragment
    private MyFragment myFragment;//我的fragment

    //Fragment的管理对象
    private FragmentManager fragmentManager;//碎片管理器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置内容视图
        setContentView(R.layout.activity_main);

        //找到控件
        tvTitle = findViewById(R.id.tv_title);
        tvWeChat = findViewById(R.id.img_wechat);
        tvContract = findViewById(R.id.img_contracts);
        tvDiscover = findViewById(R.id.img_discover);
        tvMine = findViewById(R.id.img_mine);

        //为底部的四个图片添加点击事件监听
        tvWeChat.setOnClickListener(this);
        tvContract.setOnClickListener(this);
        tvDiscover.setOnClickListener(this);
        tvMine.setOnClickListener(this);

        //初始化碎片操作
        initFragment();
    }

    /**
     * 在该方法当中，实现对四个fragment的创建（准备工作）
     * 同时，默认显示首页的fragment的内容
     */
    private void initFragment() {
        //1、拿到当前activity页面的fragment的管理对象
        fragmentManager = getSupportFragmentManager();

        //2、初始化操作，默认展示"首页"选项的碎片内容
        indexFragment = IndexFragment.newInstance();//首页fragment
        contractsFragment = new ContractsFragment();//联系人fragment
        discoverFragment = new DiscoverFragment();//发现fragment
        myFragment = new MyFragment();//我的fragment

        //3、把创建的indexFragment对象，展示到MainActivity当中的空白区域，作为默认展示
        //开启事务2
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //将indexFragment显示到空白区域处
        transaction.replace(R.id.view_fragment, indexFragment);
        //提交事务(生效）
        transaction.commit();
    }

    /**
     * 当用户点击底部的四个按钮时，程序会自动执行该方法
     *
     * @param view 用户点击的那个视图控件
     */
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.img_wechat:
                //展示微信按钮所对应的碎片视图
                showFragment(indexFragment);
                //切换标题
                tvTitle.setText("微信");
                break;
            case R.id.img_contracts:
                //展示联系人按钮所对应的碎片视图
                showFragment(contractsFragment);
                tvTitle.setText("通讯录");
                break;
            case R.id.img_discover:
                //展示发现页面对应的碎片视图
                showFragment(discoverFragment);
                tvTitle.setText("发现");
                break;
            case R.id.img_mine:
                //展示我的按钮所对应的碎片视图
                showFragment(myFragment);
                tvTitle.setText("我的");
                break;
            default:
                break;
        }
    }

    /**
     * 展示对应的碎片Fragment
     */
    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.view_fragment, fragment);
        transaction.commit();
    }

    public void open() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转到列表控件页面
     */
    public void openlist() {
        Intent intent = new Intent(this, ListViewActivity.class);
        startActivity(intent);
    }
}