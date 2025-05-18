package com.cow.entity;

import lombok.*;

/**
 *
 *
 * @description 商品表与规格表的关联实体类
 */
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSpecs {

    private Integer productId;

    private Integer specsId;
}
