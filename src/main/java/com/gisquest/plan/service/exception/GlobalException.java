package com.gisquest.plan.service.exception;

import com.gisquest.plan.service.vo.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常设置
 * Created by liul on
 * 2019/8/14 16:35
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)// 用于设置该次请求返回的状态码，若不设置，则浏览器获取的为200 status code 实际是报错的
    public ResponseResult globalException(HttpServletRequest request, Throwable ex){
        ex.printStackTrace();
        return ResponseResult.ok(getStatus(request).value(),ex.getMessage(),null);
    }

    private HttpStatus getStatus(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == null){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}

