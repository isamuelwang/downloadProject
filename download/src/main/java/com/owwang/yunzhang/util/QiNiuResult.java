package com.owwang.yunzhang.util;

/**
 * @Classname QINIUResult
 * @Description TODO
 * @Date 2020-02-13
 * @Created by WANG
 */
public class QiNiuResult {
    private String url;
    private boolean error;

    public QiNiuResult(boolean error) {
        super();
        this.error = error;
    }

    public QiNiuResult(boolean error, String url) {
        super();
        this.url = url;
        this.error = error;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public boolean isError() {
        return error;
    }
    public void setError(boolean error) {
        this.error = error;
    }
}
