package com.zyy.filter;

import com.zyy.advice.EncryptResponseBodyAdvice;
import com.zyy.entity.RestBean;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Instant;

@Component
public class SignInterceptor implements HandlerInterceptor {
    @Resource
    EncryptResponseBodyAdvice encryptResponseBodyAdvice;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String create_time = request.getHeader("XY-time");
        String request_sign = request.getHeader("XY-sign");
        if (create_time == null || request_sign == null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            RestBean<String> responseData = RestBean.failure(500, "Server Error");
            response.getWriter().write(encryptResponseBodyAdvice.getResponseBody(responseData));
            return false;
        }

        // 验证时间戳参数
        long timestamp = Long.parseLong(create_time);
        long currentTimeMillis = Instant.now().toEpochMilli();
        long timeDifferenceMillis = Math.abs(currentTimeMillis - timestamp);
        if (timeDifferenceMillis > 300000) {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            RestBean<String> responseData = RestBean.failure(500, "Server Error");
            response.getWriter().write(encryptResponseBodyAdvice.getResponseBody(responseData));
            return false;
        }

        // 验证签名sign


        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
