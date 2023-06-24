package com.qfedu.firstapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.qfedu.firstapp.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 该Activity作为应用的启动界面
 */
public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSharedPreferences：该api用于读取app安装目录下的一个指定的文件
        SharedPreferences preferences = getSharedPreferences("config", MODE_PRIVATE);
        //getXXX方法有两个参数：第一个参数表示要读取的变量名，第二个参数指的该变量的默认值
        boolean isFirst = preferences.getBoolean("is_first", true);
        if (isFirst) {//默认情况下是true，表示该app首次运行
            //跳转到引导界面，展示引导内容
            Intent intent = new Intent(this, GuideActivity.class);
            startActivity(intent);

            //跳转到引导页要进行展示时，同时将变量标志位is_first置为false
            SharedPreferences.Editor editor = preferences.edit();
            //将config文件中的is_first变量置为false，即表示该app第一次引导页已经运行过了，不是第一次运行了
            editor.putBoolean("is_first", false);
            editor.commit();//提交生效

            //要手动关闭LaunchActivity界面
            finish();
        } else {
            //设置启动页面的布局视图  （微信：地球背景页面）
            setContentView(R.layout.activity_launch);
            //设置5秒后，跳转到主界面
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    //要具体执行的定时任务的代码
                    openHome();
                }
            }, 5000);
        }
    }

    /**
     * 跳转到主界面去
     */
    private void openHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //要注意：跳转到主界面去的同时，把当前的启动界面关闭调
        finish();
    }
}