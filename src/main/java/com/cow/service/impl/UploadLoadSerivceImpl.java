package com.cow.service.impl;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;


@Service
public class UploadLoadSerivceImpl {

    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${oss.bucketName}")
    private String bucketName;

    public String uploadFile(MultipartFile file) throws Exception {
        // 1. 创建OSS实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 2. 获取文件流
        InputStream inputStream = file.getInputStream();

        // 3. 文件名（可以加点随机前缀，避免冲突）
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        // 4. 上传到OSS
        ossClient.putObject(bucketName, fileName, inputStream);

        // 5. 关闭OSS客户端
        ossClient.shutdown();

        // 6. 返回上传后的访问URL
        return "https://" + bucketName + "." + endpoint + "/" + fileName;
    }
}
