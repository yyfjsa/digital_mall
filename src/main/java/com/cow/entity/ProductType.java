package com.cow.entity;

import lombok.*;

import java.io.Serializable;

/**
 *
 *
 * @description 商品分类 实体
 */
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductType implements Serializable {
    private Integer typeId;
    private String typeName;
    private String typeDescribe;
    private String typeUrlLeft;
    private String typeUrlTop;
}
