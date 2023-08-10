package com.example.system01.utils;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 */
public class JwtUtil {

    private static long time = 1000 * 60 * 60 * 1;
    private static String sign = "admin";

    public static String createToken(Integer id, String username) {
        //创建一个JwtBuilder对象
        JwtBuilder jwtBuilder = Jwts.builder();
        //jwtToken -> abc.def.xyz
        String jwtToken = jwtBuilder
                //Header: 头部
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //Payload: 载荷
                .claim("id", id)
                .claim("username", username)
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis() + time))//Token的过期时间
                .setId(UUID.randomUUID().toString())//id字段
                //Signature：签名
                .signWith(SignatureAlgorithm.HS256, sign)//设置加密算法和签名
                //使用"."连接成一个完整的字符串
                .compact();

        return jwtToken;
    }

    /**
     * 封装验证token是否过期的方法
     */
    public static boolean checkToken(String token) {
        if (token == null || token == "") {
            return false;
        }

        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(sign).parseClaimsJws(token);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
