package com.owwang.yunzhang.controller;

import com.owwang.yunzhang.pojo.Book;
import com.owwang.yunzhang.pojo.Other;
import com.owwang.yunzhang.pojo.PdfFile;
import com.owwang.yunzhang.service.DownloadService;
import com.owwang.yunzhang.service.OtherService;
import com.owwang.yunzhang.util.QiNiuResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname DownloadController
 * @Description TODO
 * @Date 2020-02-13
 * @Created by WANG
 */
@CrossOrigin
@RestController
public class DownloadController {
    @Autowired
    private DownloadService downloadService;
    @Autowired
    private OtherService otherService;

    @PostMapping(value = "/download")
    @ResponseBody
    public QiNiuResult download(@RequestBody Book book) {
        //查询数据库，如果数据库存在数据，则返回数据库中信息即可
        Book resultBook = downloadService.download(book);
        return new QiNiuResult(true, resultBook.getQiniuurl());
    }

    @PostMapping(value = "/duxiu")
    @ResponseBody
    public QiNiuResult duxiu(@RequestBody Book book) {
        //查询数据库，如果数据库存在数据，则返回数据库中信息即可
        Book resultBook = downloadService.duxiu(book);
        return new QiNiuResult(true, resultBook.getQiniuurl());
    }

    @GetMapping("/test")
    public QiNiuResult testSql() {
        return downloadService.testSql();
    }

    @PostMapping("cookie")
    public QiNiuResult cookie(@RequestBody Other other){
        otherService.cookie(other.getCookie());
        return new QiNiuResult(true,"");
    }
}
