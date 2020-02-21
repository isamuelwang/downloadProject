package com.owwang.yunzhang.service;


import com.owwang.yunzhang.dao.Bookdao;
import com.owwang.yunzhang.dao.MyBookDao;
import com.owwang.yunzhang.dao.OtherDao;
import com.owwang.yunzhang.pojo.Book;
import com.owwang.yunzhang.pojo.PdfFile;
import com.owwang.yunzhang.util.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Classname DownloadService
 * @Description TODO
 * @Date 2020-02-13
 * @Created by WANG
 */
@Service
@Slf4j
@Transactional
public class DownloadService {
    //jpa dao
    @Autowired
    private Bookdao bookdao;
    //mybatis dao
    @Autowired
    private MyBookDao myBookDao;
    @Autowired
    private OtherDao otherDao;
    //雪花算法
    @Autowired
    IdWorker idWorker;

    public QiNiuResult testSql() {
        String s = 3 + "";
        Book book = new Book();
        book.setId((Long) idWorker.nextId());
        bookdao.save(book);
        String s2 = Integer.parseInt(s) + 1 + "";
        Book book2 = new Book();
        book2.setId((Long) idWorker.nextId());
        myBookDao.insert(book2);
        return new QiNiuResult(true, "测试成功");
    }

    //下载云展网资源
    public Book download(Book book) {
        String bookUrl = book.getBookurl();
        //查询是否已经下载了该资源
        Book existBook = bookdao.findByBookurl(bookUrl);
        if (existBook != null) {
            return existBook;
        }
        int pageTotal = book.getBooktotal();
        //设置书本的ID
        book.setId(idWorker.nextId());
        System.out.println(book);
        String fileName = UUID.randomUUID().toString().replaceAll("-", "");
        QiNiuResult pdfResult = null;
        try {
            //pdf地址
            String pdfPath = "/yunzhang/" + fileName + "/pdf/";
            //图片地址
            String picPath = "/yunzhang/" + fileName + "/pic/";
            String imgUrl = generateImgUrl(bookUrl);
            checkPath(pdfPath, picPath);
            overDownload(pageTotal, imgUrl, picPath, fileName);
            //生成pdf
            PrintToPdfUtil.toPdf(picPath, pdfPath + fileName + ".pdf", pageTotal);
            //记录创建时间
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            String time = format.format(new Date());
            book.setCreatetime(time);
            //上传到七牛云
            pdfResult = QiNiuUtil.upload("picbed8", new File(pdfPath + fileName + ".pdf"));
            book.setQiniuurl("http://cos.owwang.com/" + pdfResult.getUrl());
            book.setQiniuurl("http://cos.owwang.com/" + pdfResult.getUrl());
            //上传一张封面图片
            QiNiuResult picResult = QiNiuUtil.upload("picbed8", new File(picPath + "1.jpg"));
            //写入封面图片地址
            book.setQiniupicurl("http://cos.owwang.com/" + picResult.getUrl());
            System.out.println(book);
        } finally {
            //删除临时文件
            delFile(new File("/yunzhang/" + fileName + "/"));
            if (!pdfResult.isError()) {
                return null;
            } else {
                bookdao.save(book);
                return book;
            }
        }
    }


    //下载读秀网资源
    public Book duxiu(Book book) {
        String cookie = otherDao.findById(1).get().getCookie();
        String bookUrl = book.getBookurl();
        //查询是否已经下载了该资源
        Book existBook = bookdao.findByBookurl(bookUrl);
        if (existBook != null) {
            return existBook;
        }
        //书本总页数
        int booktotal = book.getDirtotal() + book.getFowtotal() + book.getPagetotal() + 2;
        //写入总页数
        book.setBooktotal(booktotal);
        //设置书本的ID
        book.setId(idWorker.nextId());
        System.out.println(book);
        //String fileName ="c9382d4fc7b74dd1a1193e035e0b6ca3";
        String fileName = UUID.randomUUID().toString().replaceAll("-", "");
        QiNiuResult pdfResult = null;
        //pdf地址
        String pdfPath = "/yunzhang/" + fileName + "/pdf/";
        //图片地址
        String picPath = "/yunzhang/" + fileName + "/pic/";
        checkPath(pdfPath, picPath);
        //下载资源图片
        DownloadPicUtil.overDL(book.getPicurl(), book.getBookurl(), book.getPagetotal(),
                book.getFowtotal(), book.getDirtotal(), picPath, cookie);
        //生成pdf文件名
        if (book.getBookname() != null) {
            pdfPath = pdfPath+book.getBookname() + ".pdf";
        } else {
            pdfPath = pdfPath+"1.pdf";
        }
        //生成pdf
        PrintToPdfUtil.toPdf(picPath, pdfPath, booktotal);
        //记录创建时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String time = format.format(new Date());
        book.setCreatetime(time);
        //上传到七牛云
        pdfResult = QiNiuUtil.upload("picbed8", new File(pdfPath));
        book.setQiniuurl("http://cos.owwang.com/" + pdfResult.getUrl());
        //上传一张封面图片
        QiNiuResult picResult = QiNiuUtil.upload("picbed8", new File(picPath + "1.jpg"));
        //写入封面图片地址
        book.setQiniupicurl("http://cos.owwang.com/" + picResult.getUrl());
        System.out.println(book);
        if (pdfResult != null) {
            if (!pdfResult.isError()) {
                return null;
            } else {
                bookdao.save(book);
                return book;
            }
        }
        return null;
    }

    //下载读秀网资源
    public Book duxiuRedo(Book book) {
        String cookie = otherDao.findById(1).get().getCookie();
        String bookUrl = book.getBookurl();
        //查询是否已经下载了该资源
        Book existBook = bookdao.findByBookurl(bookUrl);
        if (existBook != null) {
            return existBook;
        }
        //书本总页数
        int booktotal = book.getDirtotal() + book.getFowtotal() + book.getPagetotal() + 2;
        //写入总页数
        book.setBooktotal(booktotal);
        //设置书本的ID
        book.setId(idWorker.nextId());
        String fileName = "af8bd604dc4148deba2153684ebe14b5";
        //String fileName = UUID.randomUUID().toString().replaceAll("-", "");
        QiNiuResult pdfResult = null;
        //pdf地址
        String pdfPath = "/yunzhang/" + fileName + "/pdf/";
        //图片地址
        String picPath = "/yunzhang/" + fileName + "/pic/";
        checkPath(pdfPath, picPath);
        //下载资源图片
        //DownloadPicUtil.overDL(book.getPicurl(), book.getBookurl(), book.getPagetotal(),
        //        book.getFowtotal(), book.getDirtotal(), picPath, cookie);
        //生成pdf文件名
        if (book.getBookname() != null) {
            pdfPath = book.getBookname() + ".pdf";
        } else {
            pdfPath = "1.pdf";
        }
        //生成pdf
        PrintToPdfUtil.toPdf(picPath, pdfPath, booktotal);
        //记录创建时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String time = format.format(new Date());
        book.setCreatetime(time);
        //上传到七牛云
        pdfResult = QiNiuUtil.upload("picbed8", new File(pdfPath));
        book.setQiniuurl("http://cos.owwang.com/" + pdfResult.getUrl());
        book.setQiniuurl("http://cos.owwang.com/" + pdfResult.getUrl());
        //上传一张封面图片
        QiNiuResult picResult = QiNiuUtil.upload("picbed8", new File(picPath + "1.jpg"));
        //写入封面图片地址
        book.setQiniupicurl("http://cos.owwang.com/" + picResult.getUrl());
        System.out.println(book);
        if (pdfResult != null) {
            if (!pdfResult.isError()) {
                return null;
            } else {
                bookdao.save(book);
                return book;
            }
        }
        return null;
    }

    private boolean delFile(File file) {
        if (!file.exists()) {
            return false;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                delFile(f);
            }
        }
        return file.delete();
    }

    //遍历循环下载资源(带总页数参数）
    private void overDownload(int pageTotal, String imgUrl, String picPath, String fileName) {
        for (int i = 1; i <= pageTotal; i++) {
            log.info("正在下载地址书本每页图片：" + imgUrl + i + ".jpg");
            DownloadPicUtil.downloadPicture(imgUrl + i + ".jpg", picPath + i + ".jpg");
        }
    }

    //根据书本链接生成云展网图片链接
    private String generateImgUrl(String bookUrl) {
        //图片地址
        String imgUrl = null;
        int index = bookUrl.indexOf("/mobile");
        imgUrl = bookUrl.substring(0, index);
        return imgUrl + "/files/mobile/";
    }

    //根据书本链接生成读秀网图片链接
    private String generateImgUrl2(String bookUrl) {
        //图片地址
        String imgUrl = null;
        int index = bookUrl.indexOf("/mobile");
        imgUrl = bookUrl.substring(0, index);
        return imgUrl + "/files/mobile/";
    }

    //检查系统中是否存在目标下载文件夹
    private void checkPath(String picPath, String pdfPath) {

        File file = new File(picPath);
        File file2 = new File(pdfPath);
        //如果路径不存在，新建
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
            log.info(file.getName() + "文件夹创建成功");
        }
        if (!file2.exists() && !file2.isDirectory()) {
            file2.mkdirs();
            log.info(file2.getName() + "文件夹创建成功");
        }
    }
}
