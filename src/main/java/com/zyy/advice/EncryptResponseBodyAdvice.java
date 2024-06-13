package com.zyy.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@ControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Value("${spring.security.aes.response.key}")
    private String keyString;

    @Value("${spring.security.aes.response.iv}")
    private String ivString;

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (request.getURI().getPath().startsWith("/api/img/")) {
            // 不加密图片响应
            return body;
        }

        String ret = getResponseBody(body);
        if (ret.equals("")) {
            throw new RuntimeException("Server Error");
        }
        return ret;
    }

    public String getResponseBody(Object body) {
        try {
            String jsonString = OBJECT_MAPPER.writeValueAsString(body);
            String base64String = Base64.getEncoder().encodeToString(jsonString.getBytes(StandardCharsets.UTF_8));

            byte[] key = keyString.getBytes(StandardCharsets.UTF_8);
            byte[] iv = ivString.getBytes(StandardCharsets.UTF_8);

            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(iv));

            byte[] encrypted = cipher.doFinal(base64String.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
