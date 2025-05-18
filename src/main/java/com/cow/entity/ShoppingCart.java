package com.cow.entity;

import lombok.*;

/**
 *
 *  

 * @description 购物车实体类
 */

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {
    private Integer cartId;
    private String accountNumber;
    private Integer productId;
    private Integer payAmount;
    private String productSpecs;
}
