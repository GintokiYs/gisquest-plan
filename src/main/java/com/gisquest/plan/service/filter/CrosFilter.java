package com.gisquest.plan.service.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class CrosFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(CrosFilter.class);
    @Override
    public void destroy() {
        logger.info("销毁");
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (logger.isDebugEnabled()) {
            logger.debug("shiro session filter init~~~~~~~~~~~~");
        }

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
       // String urk = applicationConfig.getUrl();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        response.setContentType("application/json;charset=utf-8");
       // response.setHeader("Access-Control-Allow-Origin", url);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("crossDomainScriptAccessEnabled", "true");
        filterChain.doFilter(req, res);

    }

}
