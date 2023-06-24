package com.qfedu.firstapp.bean;

import java.util.List;

/**
 * 根据json数据 创建Java类时，java类名可以随便起名。
 * 但是，通常以见名知意为原则，结合具体的项目场景，根据json数据所描述的具体的
 * 内容，进行命名。不建议命名成无实际意义的类名
 */
public class Data {
    private String reason;
    private List<HotVideo> result;
    private int error_code;

    public Data() {
    }

    public Data(String reason, List<HotVideo> result, int error_code) {
        this.reason = reason;
        this.result = result;
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<HotVideo> getResult() {
        return result;
    }

    public void setResult(List<HotVideo> result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
}
