package com.zyy.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zyy.entity.Account;
import com.zyy.entity.JwtItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Component
public class JwtUtils {

    @Value("${spring.security.jwt.key}")
    String key;

    @Value("${spring.security.jwt.expireHour}")
    int expireHour;

    public JwtItem createJWT(Integer userID, String username, String role, String status) {
        Algorithm algorithm = Algorithm.HMAC256(key);
        Date nowTime = new Date();
        Date expireTime = this.getExpireTime(nowTime);
        String token = JWT.create()
                .withJWTId(UUID.randomUUID().toString())
                .withClaim("userID", userID)
                .withClaim("username", username)
                .withClaim("role", role)
                .withClaim("status", status)
                .withExpiresAt(expireTime)
                .withIssuedAt(nowTime)
                .sign(algorithm);
        return new JwtItem(userID, username, role, status, token);
    }

    public DecodedJWT resolveJwt(String headerToken) {
        String token = this.convertToken(headerToken);
        if(token == null) return null;
        Algorithm algorithm = Algorithm.HMAC256(key);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        try {
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            Date expiresAt = decodedJWT.getExpiresAt();
            if (new Date().after(expiresAt))
                return null;
            return decodedJWT;
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    public UserDetails toUser(DecodedJWT jwt) {
        Claim username = jwt.getClaim("username");
        return User
                .withUsername(username.asString())
                .password("*******")
                .build();
    }

    public Account getAccountInfo(DecodedJWT jwt) {
        Map<String, Claim> claims = jwt.getClaims();
        return new Account(claims.get("userID").asInt(), claims.get("username").asString(),
                claims.get("role").asString(), claims.get("status").asString());
    }

    private String convertToken(String headerToken) {
        if(headerToken == null || !headerToken.startsWith("Bearer "))
            return null;
        return headerToken.substring(7);
    }

    private Date getExpireTime(Date nowTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowTime);
        calendar.add(Calendar.HOUR, expireHour);
        return calendar.getTime();
    }
}
