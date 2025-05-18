package com.cow.entity;

import lombok.*;

import java.util.Date;

/**
 *
 *
 * @description 商品评价表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductReview {
    private Integer reviewId;
    private String accountNumber;
    private String productNo;
    private String productSpecs;
    private String orderNo;
    private Date reviewTime;
    private Double starLevel;
    private String productReview;
}
