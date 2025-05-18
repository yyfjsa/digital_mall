package com.cow.entity;

import lombok.*;

/**
 *
 *
 * @description 物流表
 */
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Logistics {
    private Integer logisticId;
    private String orderNo;
    private String sender;
    private String senderTel;
    private String senderAdd;
    private String receiver;
    private String receiverTel;
    private String receiverAdd;
    private String parcelName;
}
