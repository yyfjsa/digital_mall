package com.cow.entity;

import lombok.*;

/**
 *
 *
 * @description 商品轮播图表
 */
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Banner {
    private Integer bannerId;
    private String productName;
    private String productUrl;
    private String bannerUrl;
}
