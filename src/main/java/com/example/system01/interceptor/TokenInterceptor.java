package com.example.system01.interceptor;

import com.example.system01.utils.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器检测请求头里的token是否正确
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        System.out.println("获取请求头里的token为：" + token);
        if (!JwtUtil.checkToken(token)) {
            return false;
        }
        return true;
    }
}
