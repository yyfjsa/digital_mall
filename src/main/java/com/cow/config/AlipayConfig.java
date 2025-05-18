package com.cow.config;

import com.alipay.api.request.AlipayTradePagePayRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Alipay 配置类
 */
@Data
@Component
public class AlipayConfig {

    @Value("${alipay.appId}")
    private String appId;

    @Value("${alipay.url}")
    private String url;

    @Value("${alipay.charset}")
    private String charset;

    @Value("${alipay.format}")
    private String format;

    @Value("${alipay.signType}")
    private String signType;

    @Value("${alipay.notifyUrl}")
    private String notifyUrl;

    @Value("${alipay.returnUrl}")
    private String returnUrl;

    @Value("${alipay.rsaPrivateKey}")
    private String rsaPrivateKey;

    @Value("${alipay.alipayPublicKey}")
    private String alipayPublicKey;

    private AlipayConfig() {
    }

    // 多列模式的 aliPayRequest
    @Bean
    public AlipayTradePagePayRequest alipayTradePagePayRequest() {
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        return alipayRequest;
    }
}
