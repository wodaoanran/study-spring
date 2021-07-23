package com.aop.common.utils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author pwd
 * @Description:
 * @Date 2021/1/22
 * @Version 1.0
 */
public class JWTUtil {

    // 过期时间
    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    /**
     * @return boolean
     * @Description 验证token
     * @Param [token 认证令牌, userName 用户名, password 密码]
     **/
    public static boolean verify(String token, String userName, String password) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("userName", userName)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * @return java.lang.String
     * @Description 获得token中的信息无需password解密也能获得
     * @Param [token 认证令牌]
     **/
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userName").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * @return java.lang.String
     * @Description 生成token
     * @Param [userName 用户名, password 密码]
     **/
    public static String sign(String userName, String password) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Algorithm algorithm = Algorithm.HMAC256(password);
            // 附带userName信息
            return JWT.create()
                    .withClaim("userName", userName)
                    .withExpiresAt(calendar.getTime())
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}