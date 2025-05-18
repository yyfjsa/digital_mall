package com.cow.util.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.cow.config.OssConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.UUID;

/**
 *
 * 上传文件到阿里云OSS对象存储
 */
@Slf4j
@Component
public class AliyunOssUtil {

    @Autowired
    private OssConfiguration config;

    public String upload(String folderName, File file) {
        if (file == null) {
            return null;
        }

        String bucketName = config.getBucketName();
        // ❗用 OSSClientBuilder 创建，不再用 OSSClient
        OSS client = new OSSClientBuilder().build(
                config.getEndPoint(),
                config.getAccessKeyId(),
                config.getAccessKeySecret()
        );

        try {
            // 判断桶是否存在
            if (!client.doesBucketExist(bucketName)) {
                // 创建桶
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                client.createBucket(createBucketRequest);
            }

            // 设置文件路径和名称（注意：不要把 fileHost 拼进去！！）
            String filePath = folderName + "/" + UUID.randomUUID().toString().replace("-", "") + "-" + file.getName();
            PutObjectResult result = client.putObject(new PutObjectRequest(bucketName, filePath, file));
            client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);

            // 上传成功后返回公网访问 URL
            if (result != null) {
                String fileUrl = "https://" + bucketName + "." + config.getEndPoint() + "/" + filePath;
                return fileUrl;
            }
        } catch (Exception e) {
            log.error("上传文件到阿里云OSS失败", e);
        } finally {
            client.shutdown();
        }
        return null;
    }
}
