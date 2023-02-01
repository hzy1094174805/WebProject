package com.itheima.reggie.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理程序
 *
 * @author 14220
 * @date 2023/02/01
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R method1(SQLIntegrityConstraintViolationException e) {
//        System.out.println(e.getMessage());
        if (e.getMessage().contains("Duplicate entry")) {
            String[] s = e.getMessage().split(" ");
            return R.error(s[2] + "已经存在");
        }

        return R.error("未知错误");
    }
}
