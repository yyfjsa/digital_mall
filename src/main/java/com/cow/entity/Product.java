package com.cow.entity;

import lombok.*;

import java.util.Date;

/**
 *
 *  

 * @description 商品表实体类
 */

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 商品编号
     */
    private String productNo;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品分类
     */
    private String productType;

    /**
     * 商品描述
     */
    private String productDescribe;

    /**
     * 商品品牌
     */
    private String productBrand;

    /**
     * 商品进价
     */
    private Double inPrice;

    /**
     * 商品售价
     */
    private Double outPrice;

    /**
     * 商品库存
     */
    private Integer productStock;

    /**
     * 最低库存
     */
    private Integer lowestStock;

    /**
     * 是否缺货
     */
    private Boolean isStockOut;

    /**
     * 是否新品
     */
    private Boolean isNew;

    /**
     * 是否上架
     */
    private Boolean isSale;

    /**
     * 上架时间
     */
    private Date saleTime;

    /**
     * 图片链接
     */
    private String productUrl;
}
