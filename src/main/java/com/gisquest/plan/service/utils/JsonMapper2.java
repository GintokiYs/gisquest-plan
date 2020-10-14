package com.gisquest.plan.service.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.gisquest.plan.service.common.GlobalServiceException;
import com.gisquest.plan.service.common.ResponseStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
/**
 * @ClassName JsonMapper2
 * @Description 针对mvc对多态支持json数据中加入类名，需要使用定制ObjectMapper进行反解析
 * @Author lull
 * @Date 2019/8/6 14:27
 * @Version 1.0
 */
@Component
public class JsonMapper2 {

    private static ObjectMapper mapper;

    public JsonMapper2(ObjectMapper mapper) {
        JsonMapper2.mapper = mapper;
    }

    /**
     * 对象转换为JSON字符串
     *
     * @param object
     * @return
     */
    public static String toJsonString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            System.out.println("json格式化错误");
            throw new GlobalServiceException(ResponseStatus.COMMON_FAIL);
        }
    }

    /**
     * JSON字符串转换为对象
     *
     * @param jsonString
     * @param clazz
     * @return
     */
    public static Object fromJsonString(String jsonString, Class<?> clazz) {
        try {
            if (StringUtils.isNotEmpty(jsonString)) {
                return mapper.readValue(jsonString, clazz);
            } else {
                return null;
            }
        } catch (IOException e) {
            System.out.println("json格式化错误");
            throw new GlobalServiceException(ResponseStatus.COMMON_FAIL);
        }
    }

}
