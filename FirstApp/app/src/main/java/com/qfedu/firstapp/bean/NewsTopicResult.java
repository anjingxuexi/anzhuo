package com.qfedu.firstapp.bean;

import java.util.List;

public class NewsTopicResult {
    private String stat;
    private List<NewsTopic> data;
    private String page;
    private String pageSize;

    public NewsTopicResult() {
    }

    public NewsTopicResult(String stat, List<NewsTopic> data, String page, String pageSize) {
        this.stat = stat;
        this.data = data;
        this.page = page;
        this.pageSize = pageSize;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public List<NewsTopic> getData() {
        return data;
    }

    public void setData(List<NewsTopic> data) {
        this.data = data;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
