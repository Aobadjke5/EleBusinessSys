package com.zyy.filter;


import com.zyy.advice.EncryptResponseBodyAdvice;
import com.zyy.entity.RestBean;
import com.zyy.utils.EncipherUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@WebFilter(filterName = "headerFilter", urlPatterns = "/*")
public class HeaderFilter implements Filter {
    @Resource
    EncryptResponseBodyAdvice encryptResponseBodyAdvice;

    @Resource
    EncipherUtils encipherUtils;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;

            // 检查请求URL
            String requestURI = httpServletRequest.getRequestURI();

            // 排除特定的URL
            if (requestURI.startsWith("/api/img/")) {
                chain.doFilter(request, response);
                return;
            }

            CachedBodyHttpServletRequest cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest(httpServletRequest);

            // 读取请求体内容
            String body = new String(cachedBodyHttpServletRequest.getInputStream().readAllBytes());
            if (body.equals("")) {
                body = "\"\"";
            }

            if (requestURI.equals("/api/auth/login")) {
                body = "\"" + body + "\"";
            }
            // 获取请求头
            String XY_time = httpServletRequest.getHeader("XY-time");
            String XY_sign = httpServletRequest.getHeader("XY-sign");
            if (XY_time == null || XY_sign == null) {
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                RestBean<String> responseData = RestBean.failure(500, "Server Error");
                response.getWriter().write(encryptResponseBodyAdvice.getResponseBody(responseData));
                return ;
            }
            // 验证sign参数

            String md5Value = encipherUtils.getMD5(body + XY_time);
            if (!md5Value.equals(XY_sign)) {
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                RestBean<String> responseData = RestBean.failure(500, "Server Error");
                response.getWriter().write(encryptResponseBodyAdvice.getResponseBody(responseData));
                return ;
            }

            if (requestURI.equals("/api/auth/login")) {
                body = body.substring(1, body.length() - 1);
            }
            // 继续处理请求，将包装后的请求传递给下一个过滤器或目标servlet
            chain.doFilter(cachedBodyHttpServletRequest, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
