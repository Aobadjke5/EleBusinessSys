package com.zyy.controller;

import com.zyy.entity.RestBean;
import com.zyy.utils.FileUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class FileController {

    @Resource
    FileUtils fileUtils;

    @Value("${upload.directory}")
    private String uploadDirectory;

    @Value("${upload.maxMBSize}")
    private int maxFileSize;

    public static class File {
        public File(String fileName, String fileURL) {
            FileName = fileName;
            FileURL = fileURL;
        }

        public String FileName;
        public String FileURL;
    }

    @RequestMapping("/upload")
    public RestBean<File> upload(@RequestParam("file") MultipartFile file, @RequestParam("type") String type) {
        if (file.isEmpty())
            return RestBean.failure(400, "Empty file");

        if (!fileUtils.isImage(file))
            return RestBean.failure(400, "Wrong file");

        if (file.getSize() > (long) maxFileSize * 1024 * 1024)
            return RestBean.failure(400, "The size of the uploaded file exceeds the limit");

        if (!type.equals("product") && !type.equals("company") && !type.equals("warehouse"))
            return RestBean.wrongPara();

        try {
            String fileMD5 = fileUtils.getFileMd5(file);

            String fileURL = "/api/img/" + type + "/" + fileMD5;
            String newFileName = fileMD5 + ".jpg";
            Path path = Paths.get(uploadDirectory);
            Path folder = path.resolve(type);
            path = folder.resolve(newFileName);

            if(!Files.exists(folder)) {
                Files.createDirectories(folder);
            }

            byte[] bytes = file.getBytes();
            Files.write(path, bytes);
            return RestBean.success(new File(newFileName, fileURL));
        } catch (IOException e) {
            return RestBean.failure(500, "Upload error");
        }
    }

    @RequestMapping("/img/{imageType}/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageType, @PathVariable String imageName) throws IOException {
        imageName += ".jpg";
        Path path = Paths.get(uploadDirectory, imageType, imageName);

        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        byte[] imageBytes = Files.readAllBytes(path);
        MediaType mediaType = MediaType.IMAGE_JPEG;
        return ResponseEntity.ok().contentType(mediaType).body(imageBytes);
    }
}
