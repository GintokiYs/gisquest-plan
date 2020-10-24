package com.gisquest.plan.service.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlEncodeChinese {
    /**
     * 将中文转为url编码
     * @param url
     * @return
     */
    public static String urlEncodeChinese(String url) {
        try {
            Matcher matcher = Pattern.compile("[\\u4e00-\\u9fa5]").matcher(url);
            String tmp = "";
            while (matcher.find()) {
                tmp = matcher.group();
                url = url.replaceAll(tmp, URLEncoder.encode(tmp, "UTF-8"));
            }
            // 解决英文","下载不了问题
            url = url.replaceAll(",","%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }
}
