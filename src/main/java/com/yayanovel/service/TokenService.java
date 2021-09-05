package com.yayanovel.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.yayanovel.entity.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * token服务类
 */
@Service
public class TokenService {
    @Value("${token.effectiveTime}")
    private String effectiveTime;
    public String getToken(UserInfo user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + Long.parseLong(effectiveTime);//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(user.getUserUid()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getUserPassword()));
        return token;
    }
}
