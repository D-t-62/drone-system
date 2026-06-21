package com.example.dronesystem.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestLogInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestLogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("========================================");
        logger.info("请求信息开始");
        logger.info("请求方法: {}", request.getMethod());
        logger.info("请求URL: {}", request.getRequestURI());
        logger.info("请求参数: {}", request.getQueryString());
        logger.info("客户端IP: {}", request.getRemoteAddr());
        logger.info("请求时间: {}", System.currentTimeMillis());
        logger.info("请求信息结束");
        logger.info("========================================");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.info("========================================");
        logger.info("响应信息开始");
        logger.info("响应状态码: {}", response.getStatus());
        logger.info("响应时间: {}", System.currentTimeMillis());
        if (ex != null) {
            logger.error("请求异常: {}", ex.getMessage());
        }
        logger.info("响应信息结束");
        logger.info("========================================");
    }
}