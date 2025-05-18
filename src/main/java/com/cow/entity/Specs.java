package com.cow.entity;

import lombok.*;

import java.io.Serializable;

/**
 *
 *  
 * @description 商品规格表   eg：服装 M、X、XL尺码等信息
 */

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Specs implements Serializable {
    private Integer specsId;
    private String specsName;
    private String productType;
}
