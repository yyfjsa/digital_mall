package com.cow.entity;

import lombok.*;

import java.util.Date;

/**
 *
 *
 * @description 采购表
 */
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    /**
     * 采购id
     */
    private Integer purchaseId;

    /**
     * 采购编号
     */
    private String purchaseNo;

    /**
     * 采购数量
     */
    private String purchaseNumber;

    /**
     * 采购时间
     */
    private Date purchaseTime;

    /**
     * 收货时间
     */
    private Date receiptTime;

    /**
     * 商品编号
     */
    private String productNo;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 供应商编号
     */
    private String supplierNo;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 后台操作员账号
     */
    private String accountNumber;

    /**
     * 后台操作员名称
     */
    private String userName;

    /**
     * 是否收货
     */
    private Boolean receiptStatus;
}
