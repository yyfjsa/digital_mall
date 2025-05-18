package com.cow.entity;

import lombok.*;

/**
 *
 *  
 * @description 供应商表
 */
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    private Integer supplierId;
    private String supplierNo;
    private String supplierName;
    private String productType;

    /**
     * 负责人
     */
    private String principal;

    /**
     * 联系方式
     */
    private String contactWay;

    /**
     * 是否可用
     */
    private Boolean status;
}
