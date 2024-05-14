package com.zyy.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Component
public class FileUtils {

    public String getFileMd5(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        return DigestUtils.md5Hex(inputStream);
    }

    public boolean isImage(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }
}
