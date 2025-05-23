package com.cow.service.impl;

import com.cow.dao.UserRoleDao;
import com.cow.entity.UserRole;
import com.cow.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 *
 *  
 * @description 用户角色业务逻辑
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<UserRole> selectByUserId(Integer userId) {
        return userRoleDao.selectByUserId(userId);
    }

    @Override
    public List<UserRole> selectAll() {
        return userRoleDao.selectAll();
    }

    @Override
    public Boolean insertData(UserRole userRole) {
        return userRoleDao.insertData(userRole);
    }

    @Override
    public Boolean deleteById(Integer userId) {
        return userRoleDao.deleteById(userId);
    }

    @Override
    public List<Map<String, Object>> selectRoleByUserId(Integer userId) {
        return userRoleDao.selectRoleByUserId(userId);
    }

    @Override
    public Boolean existsRole(Integer roleId) {
        return userRoleDao.existsRole(roleId);
    }
}
