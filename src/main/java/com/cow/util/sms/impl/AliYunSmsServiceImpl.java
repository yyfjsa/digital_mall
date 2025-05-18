package com.cow.util.sms.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.cow.util.sms.AliYunSmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Slf4j
@Service
public class AliYunSmsServiceImpl implements AliYunSmsService {

    /**
     * accessKeyID
     */
    @Value("${sms.aliyun.accessKeyID}")
    private String accessKeyId;

    /**
     * accessKeySecret
     */
    @Value("${sms.aliyun.accessKeySecret}")
    private String accessKeySecret;

    /**
     * 短信api的请求地址  固定
     */
    @Value("${sms.aliyun.domain}")
    private String domain;

    /**
     * 指定地域名称 短信API cn-hangzhou 固定
     */
    @Value("${sms.aliyun.regionId}")
    private String regionId;

    /**
     * 短信签名
     */
    @Value("${sms.aliyun.signName}")
    private String signName;

    /**
     * 短信模板
     */
    @Value("${sms.aliyun.templateCode}")
    private String templateCode;

    @Override
    public boolean sendSms(String phone, String message) {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(domain);
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);

        // 修复点：严格匹配模板变量名
        Map<String, String> params = new HashMap<>();
        params.put("code", message); // 根据实际模板变量名调整
        request.putQueryParameter("TemplateParam", JSON.toJSONString(params));

        try {
            CommonResponse response = client.getCommonResponse(request); // 取消注释
            if (response.getHttpStatus() == 200) {
                log.info("短信发送成功！响应数据：{}", response.getData());
                return true;
            } else {
                log.error("发送失败，错误码：{}，错误信息：{}",
                        response.getHttpStatus(), response.getData());
                return false;
            }
        } catch (Exception e) {
            log.error("阿里云短信发送异常：", e); // 打印完整异常栈
            return false;
        }
    }
}
