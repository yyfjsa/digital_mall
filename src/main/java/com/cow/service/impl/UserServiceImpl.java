package com.cow.service.impl;

import com.cow.constant.UserStatusEnum;
import com.cow.dao.UserDao;
import com.cow.entity.Role;
import com.cow.entity.User;
import com.cow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 *
 *  
 * @description 用户业务逻辑
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User selectById(Integer id) {
        return userDao.selectById(id);
    }

    @Override
    public User selectByKey(String key) {
        return userDao.selectByKey(key);
    }

    @Override
    public List<Role> getRoleList(Integer userId) {
        return userDao.getRoleList(userId);
    }

    @Override
    public User selectByPhone(String telephone) {
        return userDao.selectByPhone(telephone);
    }

    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }

    @Override
    public Integer selectCount() {
        return userDao.selectCount();
    }

    @Override
    public Boolean selectUserState(String accountNumber) {
        return userDao.selectUserState(accountNumber);
    }

    @Override
    public Boolean existsWithPrimaryKey(String existUser) {
        return userDao.existsWithPrimaryKey(existUser);
    }

    @Override
    public Boolean existsWithPrimaryPhone(String telephone) {
        return userDao.existsWithPrimaryPhone(telephone);
    }

    @Override
    public Integer selectIdByKey(String key) {
        return userDao.selectIdByKey(key);
    }

    @Override
    public Boolean insertData(User user) {
        user.setCreatTime(new Date());
        return userDao.insertData(user);
    }

    @Override
    public Boolean updateById(User user) {
        return userDao.updateById(user);
    }

    @Override
    public Boolean deleteById(Integer id) {
        return userDao.deleteById(id);
    }

    @Override
    public List<User> queryAllByStatus(UserStatusEnum status) {
        return userDao.queryAllByStatus(status);
    }
}
