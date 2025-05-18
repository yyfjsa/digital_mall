package com.cow.entity;

import lombok.*;

import java.util.Date;

/**
 *
 *
 * @description 商品退货表
 */
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnGoods {
    private Integer returnId;
    private Date applyTime;
    private String userNumber;
    private String userName;
    private Double returnPrice;
    private String returnState;
    private Date dealTime;
    private String orderNo;
    private String operatorNumber;
    private String operatorName;
    private String returnReason;
}
