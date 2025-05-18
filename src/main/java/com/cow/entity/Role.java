package com.cow.entity;

import lombok.*;

import java.io.Serializable;

/**
 *
 *
 * @description 用户权限
 */
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 角色昵称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDescribe;

    /**
     * 是否启用
     */
    private Boolean roleState;
}
