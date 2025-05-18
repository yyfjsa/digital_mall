package com.cow.entity;

import lombok.*;

/**
 *
 *
 * @description 退货原因表
 */
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnReason {
    private Integer reasonId;
    private String reasonName;
    private Boolean status;
}
