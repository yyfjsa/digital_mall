package com.cow.entity;

import lombok.*;

import java.io.Serializable;

/**
 *
 *
 * @description 商品品牌 实体
 */
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductBrand implements Serializable {
    private Integer brandId;
    private String brandName;
    private String brandDescribe;
}
