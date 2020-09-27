package com.gisquest.plan.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 常用量
 * @author honght
 * @date 2020/9/27 10:13
 */
@Configuration
@PropertySource("classpath:constant.properties")
public class Constant {
    public static final String CONTENTTYPE = "Content-Type";
    public static final String FORMLOGIN = "application/x-www-form-urlencoded; charset=UTF-8";
    public static final String JSONLOGIN = "application/json";
}
