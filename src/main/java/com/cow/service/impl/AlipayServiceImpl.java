package com.cow.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.cow.config.AlipayConfig;
import com.cow.service.AlipayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @description 支付宝业务逻辑
 */
@Slf4j
@Service
public class AlipayServiceImpl implements AlipayService {
    private static final String PRODUCTCODE = "FAST_INSTANT_TRADE_PAY";

    private final AlipayConfig alipayConfig;

    // 构造器注入 AlipayConfig
    @Autowired
    public AlipayServiceImpl(AlipayConfig alipayConfig) {
        this.alipayConfig = alipayConfig;
    }

    @Override
    public String create(String orderId, String orderName, String payPrice) {
        AlipayClient client = new DefaultAlipayClient(
                alipayConfig.getUrl(),
                alipayConfig.getAppId(),
                alipayConfig.getRsaPrivateKey(),
                alipayConfig.getFormat(),
                alipayConfig.getCharset(),
                alipayConfig.getAlipayPublicKey(),
                alipayConfig.getSignType()
        );
        AlipayTradePagePayRequest payRequest = new AlipayTradePagePayRequest();
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(orderId);
        model.setSubject(orderName);
        model.setTotalAmount(payPrice);
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        payRequest.setBizModel(model);
        payRequest.setReturnUrl(alipayConfig.getReturnUrl());
        payRequest.setNotifyUrl(alipayConfig.getNotifyUrl());
        try {
            return client.pageExecute(payRequest).getBody();
        } catch (AlipayApiException e) {
            log.error("[支付宝] 支付失败", e);
        }
        return null;
    }

    @Override
    public void refund(String orderId, String payPrice) throws AlipayApiException {
        AlipayClient client = new DefaultAlipayClient(
                alipayConfig.getUrl(),
                alipayConfig.getAppId(),
                alipayConfig.getRsaPrivateKey(),
                alipayConfig.getFormat(),
                alipayConfig.getCharset(),
                alipayConfig.getAlipayPublicKey(),
                alipayConfig.getSignType()
        );
        AlipayTradeRefundRequest payRequest = new AlipayTradeRefundRequest();
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setOutTradeNo(orderId);
        model.setRefundAmount(payPrice);
        payRequest.setBizModel(model);
        client.execute(payRequest).getBody();
    }
}

