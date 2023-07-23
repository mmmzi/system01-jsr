package com.example.system01.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {

    public static final String SALT = "myjava";

    public static String CreateToken(String id, String name) {

        long now = System.currentTimeMillis();
        long exp = now + 100 * 60 * 60;//设置过期时间60分钟
        //创建JWT对象
        JwtBuilder builder = Jwts.builder().setId("0001")
                .setSubject("admin")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SALT)
                .setExpiration(new Date(exp));

        //构建token
        return builder.compact();
    }

    public static String ParseToken(String token) {
        //解析Token，生成Claims对象，Token中存放的用户信息解析到claims对象中
        Claims claims = Jwts.parser().setSigningKey(SALT).parseClaimsJws(token).getBody();
        return claims.getId();
    }
}
