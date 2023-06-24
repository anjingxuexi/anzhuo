package com.qfedu.firstapp;

/**
 * 定义该常量类，用于存放项目当中内容值不变的数据，比如配置信息等
 */
public class Const {
    //定义每次请求数据的条数
    public static final int PAGE_SIZE = 30;

    //聚合数据网站：热门视频 接口的KEY
    public static final String HOTVIDEO_KEY = "e4e086097a1bf1f0b8996b28e22724bd";
    public static final String HOTVIDEO_URL = "http://apis.juhe.cn/fapig/douyin/billboard";

    //头条新闻
    public static final String NEWSTOPIC_URL = "http://v.juhe.cn/toutiao/index";
    public static final String NEWSTOPIC_KEY = "5dd02a8f46fd45cbdb7c6fb2b64eb91f";
}
