package com.qfedu.firstapp.bean;

/**
 * 新闻头条的返回数据类的定义
 */
public class NewsTopicReturn {
    private String reason;
    private NewsTopicResult result;
    private int error_code;

    public NewsTopicReturn() {
    }

    public NewsTopicReturn(String reason, NewsTopicResult result, int error_code) {
        this.reason = reason;
        this.result = result;
        this.error_code = error_code;
    }

    public NewsTopicResult getResult() {
        return result;
    }

    public void setResult(NewsTopicResult result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
