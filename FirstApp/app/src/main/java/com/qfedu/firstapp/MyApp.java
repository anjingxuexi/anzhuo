package com.qfedu.firstapp;

import android.app.Application;

import org.xutils.x;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //该onCreate方法会在项目首次加载时，由系统自动调用
        //项目开发当中，把需要进行项目初始化操作的的代码放在此处执行
        x.Ext.init(this);//xutils库的初始化操作
        x.Ext.setDebug(true);
    }
}
