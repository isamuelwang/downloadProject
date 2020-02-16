package com.owwang.yunzhang.service;

import com.owwang.yunzhang.dao.OtherDao;
import com.owwang.yunzhang.pojo.Other;
import com.owwang.yunzhang.util.QiNiuResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname OtherService
 * @Description TODO
 * @Date 2020-02-16
 * @Created by WANG
 */
@Service
public class OtherService {
    @Autowired
    private OtherDao otherDao;

    public void cookie(String cookie){
        Other dbOther = otherDao.findById(1).get();
        dbOther.setCookie(cookie);
        otherDao.save(dbOther);
    }
}
