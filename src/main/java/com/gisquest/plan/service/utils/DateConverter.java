package com.gisquest.plan.service.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author honght
 * @date 2020/9/27 10:13
 */
public class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(StringUtils.isEmpty(s)){
            return null;
        }
        try {
            return sf.parse(sf.format(new Date(Long.parseLong(s))));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
