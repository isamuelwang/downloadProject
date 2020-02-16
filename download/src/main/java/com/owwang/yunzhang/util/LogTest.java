package com.owwang.yunzhang.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @Classname LogTest
 * @Description TODO
 * @Date 2020-02-13
 * @Created by WANG
 */
@Slf4j
public class LogTest {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("----> logback start");
        log.trace("--> Hello trace.");
        log.debug("--> Hello debug.");
        log.info("--> Hello info.");
        log.warn("--> Goodbye warn.");
        log.error("--> Goodbye error.");
        System.out.println("----> logback end");
    }
}
