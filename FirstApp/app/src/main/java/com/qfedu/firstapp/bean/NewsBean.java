package com.qfedu.firstapp.bean;

import java.io.Serializable;

/**
 * 自定义类：新闻类
 * 根据要展示的列表的内容：新闻列表
 * 分析和总结每一条新闻要展示的信息包含哪些内容：
 * ① 标题
 * ② 简介
 * ③ 来源
 * ④ 阅读数
 * ⑤ 评论数
 * ⑥ 转发数
 */
public class NewsBean implements Serializable {
    private String title;
    private String description;
    private String original;
    private int read;
    private int comment;
    private int share;

    //无参构造方法
    public NewsBean() {
    }

    //全参构造
    public NewsBean(String title, String description, String original, int read, int comment, int share) {
        this.title = title;
        this.description = description;
        this.original = original;
        this.read = read;
        this.comment = comment;
        this.share = share;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }
}
