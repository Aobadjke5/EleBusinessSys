package com.zyy.utils;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
public class JwtUtilsTest {

    @Autowired
    JwtUtils jwtUtils;

    @Test
    public void getUserTest() {
        String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQWRtaW4iLCJleHAiOjE3MTU1OTkzMjksInVzZXJJRCI6MSwiaWF0IjoxNzE1NTc3NzI5LCJ1c2VybmFtZSI6InRlc3QiLCJzdGF0dXMiOiJWZXJpZmllZCJ9.N6-T8WilxpiRCXOq9P813tdAyQGLPTIiIqMLTiUhUd4";
        DecodedJWT decodedJWT = jwtUtils.resolveJwt(token);
        System.out.println(decodedJWT);
        UserDetails userDetails = jwtUtils.toUser(decodedJWT);
        System.out.println(userDetails);
    }
}
