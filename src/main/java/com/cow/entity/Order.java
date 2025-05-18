package com.cow.entity;

import lombok.*;

import java.util.Date;

/**
 *
 *
 * @description 订单表 实体类
 */
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 下单时间
     */

    private Date orderTime;

    /**
     * 商品编号
     */
    private String productNo;

    /**
     * 商品规格
     */
    private String productSpecs;

    /**
     * 用户账户
     */
    private String userAccount;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 联系方式
     */
    private String contactWay;

    /**
     * 支付金额
     */
    private Double payPrice;

    /**
     * 购买数量
     */
    private Integer payAmount;

    /**
     * 支付方式
     */
    private String payType;

    /**
     * 订单来源
     */
    private String orderFrom;

    /**
     * 订单状态
     */
    private String orderState;

    /**
     * 收货地址
     */
    private String acceptAddress;

    /**
     * 退货状态
     */
    private Boolean returnState;
}
