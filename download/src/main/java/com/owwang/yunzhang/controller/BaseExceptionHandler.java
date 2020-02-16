package com.owwang.yunzhang.controller;
import com.owwang.yunzhang.util.QiNiuResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
public class BaseExceptionHandler {
	
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public QiNiuResult error(Exception e){
        e.printStackTrace();
        return new QiNiuResult(false);
    }
}
