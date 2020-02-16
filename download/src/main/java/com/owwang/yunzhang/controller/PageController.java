package com.owwang.yunzhang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname PageController
 * @Description TODO
 * @Date 2020-02-13
 * @Created by WANG
 */
@Controller
public class PageController {

    @RequestMapping(value={"/index","/","/y"})
    public String index() {
        return "t";
    }

    @RequestMapping(value={"/c"})
    public String cookie() {
        return "t1";
    }

    @RequestMapping(value={"/a"})
    public String duxiu() {
        return "t2";
    }
}
