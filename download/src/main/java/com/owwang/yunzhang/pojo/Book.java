package com.owwang.yunzhang.pojo;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * book
 * @author 
 */
@Entity
@Table(name="book")
public class Book implements Serializable {
    /**
     * 条目的id，自增
     */
    @Id
    private Long id;

    /**
     * isbn号
     */
    private String isbn;

    /**
     * 书籍名称
     */
    private String bookname;

    /**
     * 书籍页数
     */
    private Integer pagetotal;

    /**
     * 前言页数
     */
    private Integer fowtotal;

    /**
     * 目录页数
     */
    private Integer dirtotal;

    /**
     * 总页数
     */
    private Integer booktotal;

    /**
     * 书籍介绍
     */
    private String bookintro;

    /**
     * 书籍url地址
     */
    private String bookurl;

    /**
     * 第一张图片url
     */
    private String picurl;

    /**
     * 千牛云url地址
     */
    private String qiniuurl;

    /**
     * pdf文件大小
     */
    private String filebig;

    /**
     * 千牛云封面图片地址
     */
    private String qiniupicurl;

    /**
     * 百度云url地址
     */
    private String baiduyunurl;

    /**
     * 创建时间
     */
    private String createtime;

    private static final long serialVersionUID = 1L;

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public Integer getPagetotal() {
        return pagetotal;
    }

    public void setPagetotal(Integer pagetotal) {
        this.pagetotal = pagetotal;
    }

    public Integer getFowtotal() {
        return fowtotal;
    }

    public void setFowtotal(Integer fowtotal) {
        this.fowtotal = fowtotal;
    }

    public Integer getDirtotal() {
        return dirtotal;
    }

    public void setDirtotal(Integer dirtotal) {
        this.dirtotal = dirtotal;
    }

    public Integer getBooktotal() {
        return booktotal;
    }

    public void setBooktotal(Integer booktotal) {
        this.booktotal = booktotal;
    }

    public String getBookintro() {
        return bookintro;
    }

    public void setBookintro(String bookintro) {
        this.bookintro = bookintro;
    }

    public String getBookurl() {
        return bookurl;
    }

    public void setBookurl(String bookurl) {
        this.bookurl = bookurl;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getQiniuurl() {
        return qiniuurl;
    }

    public void setQiniuurl(String qiniuurl) {
        this.qiniuurl = qiniuurl;
    }

    public String getFilebig() {
        return filebig;
    }

    public void setFilebig(String filebig) {
        this.filebig = filebig;
    }

    public String getQiniupicurl() {
        return qiniupicurl;
    }

    public void setQiniupicurl(String qiniupicurl) {
        this.qiniupicurl = qiniupicurl;
    }

    public String getBaiduyunurl() {
        return baiduyunurl;
    }

    public void setBaiduyunurl(String baiduyunurl) {
        this.baiduyunurl = baiduyunurl;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", bookname='" + bookname + '\'' +
                ", pagetotal=" + pagetotal +
                ", fowtotal=" + fowtotal +
                ", dirtotal=" + dirtotal +
                ", booktotal=" + booktotal +
                ", bookintro='" + bookintro + '\'' +
                ", bookurl='" + bookurl + '\'' +
                ", picurl='" + picurl + '\'' +
                ", qiniuurl='" + qiniuurl + '\'' +
                ", filebig='" + filebig + '\'' +
                ", qiniupicurl='" + qiniupicurl + '\'' +
                ", baiduyunurl='" + baiduyunurl + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }
}