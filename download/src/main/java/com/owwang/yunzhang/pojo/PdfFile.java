package com.owwang.yunzhang.pojo;

import java.io.Serializable;

/**
 * @Classname PdfFile
 * @Description TODO
 * @Date 2020-02-13
 * @Created by WANG
 */
public class PdfFile implements Serializable {
    String fileName;
    String bookUrl;
    int pageTotal;

    public PdfFile(String fileName, String bookUrl, int pageTotal) {
        this.fileName = fileName;
        this.bookUrl = bookUrl;
        this.pageTotal = pageTotal;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }
}
