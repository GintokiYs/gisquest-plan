package com.gisquest.plan.service.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gisquest.plan.service.vo.ResponseResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;

/**
 * HTTP工具类
 */
public class HttpUtils {

	/**
	 * 获取HttpServletRequest对象
	 * @return
	 */
	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * 输出信息到浏览器
	 * @param response
	 * @param data
	 * @throws IOException
	 */
	public static void write(HttpServletResponse response, Object data) throws IOException {
		response.setContentType("application/json; charset=utf-8");
		ResponseResult result = ResponseResult.ok(data);
		String json = JSONObject.toJSONString(result);
		response.getWriter().print(json);
		response.getWriter().flush();
		response.getWriter().close();
	}

}
