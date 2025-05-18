package com.cow.entity;

import lombok.*;

import java.io.Serializable;

/**
 *
 *
 * @description 用户表 与 角色表的关联表
 */
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRole implements Serializable {
    private Integer userId;
    private Integer roleId;
}
