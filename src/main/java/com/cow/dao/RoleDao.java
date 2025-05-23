package com.cow.dao;

import com.cow.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 *
 * @description role表
 */
public interface RoleDao extends BaseDao<Role> {
    /**
     * 查询所有可用的角色
     *
     * @return 角色信息
     */
    List<Role> selectAllUsable();

    /**
     * 查询角色名称是否存在
     *
     * @param roleId   角色编号
     * @param roleName 角色名称
     * @return 是否存在
     */
    Boolean existsRoleName(@Param("roleId") Integer roleId, @Param("roleName") String roleName);
}
