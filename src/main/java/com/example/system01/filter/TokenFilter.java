/*
//package com.example.system01.filter;
//
//import com.google.common.collect.Sets;
//import net.minidev.json.JSONObject;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.Serializable;
//import java.util.*;
//
//import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;
//
//@Component
//public class TokenFilter extends OncePerRequestFilter {
//
//    private static final Set<String> ALLOW_PATH =
//            Collections.unmodifiableSet(Sets.newHashSet("http://localhost:8080","/","/login","/login.html","/doc","/doc.html","/doc.html#/home","/webjars/**","/META-INF/resources/","/META-INF/resources/webjars/"));
//
//    public static final String TOKEN = "token";
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String uri = request.getRequestURI();
//        if(ALLOW_PATH.contains(uri)){
//            filterChain.doFilter(request,response);
////            return;
//        }
//        else {
//            //从请求中获取token
//            String token = getToken(request);
//            System.out.println("mytoken is :"+token);
//            if(StringUtils.hasText(token)){
//                return;
//            }else {
//                response.setHeader("Access-Control-Allow-Origin","*");
//                response.setHeader("Access-Control-Allow-Methods","*");
//                response.setContentType("application/json;charset=UTF-8");
//                Map<String,Object> map = new HashMap<>();
//                map.put("code",401);
//                map.put("msg","没有权限访问");
////            response.getWriter().write(JSON.toJSONString(map));
//                JSONObject json = new JSONObject(map);
//                response.getWriter().write(String.valueOf(json));
//            }
//        }
//
//    }
//
//    private String getToken(HttpServletRequest request){
//
//        String token = request.getHeader(TOKEN);
//        if(!StringUtils.hasText(token)){
//            Cookie[] cookies = request.getCookies();
//            if(Objects.nonNull(cookies)&&cookies.length>0){
//                for(Cookie cookie:cookies){
//                    if(Objects.equals(cookie.getName(),TOKEN)){
//                        token = cookie.getValue();
//                    }
//                }
//                if(!StringUtils.hasText(token)){
//                    token = request.getParameter(TOKEN);
//                }
//            }
//        }
//        return token;
//    }
//}
*/
