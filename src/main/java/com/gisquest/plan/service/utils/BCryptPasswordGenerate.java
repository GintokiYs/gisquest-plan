package com.gisquest.plan.service.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author honght
 * @date 2020/9/27 10:13
 */
public class BCryptPasswordGenerate {
    public static void main(String[] args) {
        String passWord = new BCryptPasswordEncoder().encode("admin");
        System.out.println(passWord);
    }
}
