package com.cow;


import cn.dev33.satoken.secure.SaSecureUtil;
import com.cow.constant.UserStatusEnum;
import com.cow.entity.SuperAdmin;
import com.cow.entity.User;
import com.cow.entity.UserRole;
import com.cow.service.UserRoleService;
import com.cow.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class BreadRollMallServerRunner implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(BreadRollMallServerRunner.class);
    private static final String DEFAULT_USERNAME = "超级管理员";
    private static final String DEFAULT_PASSWORD = "123456";
    SuperAdmin superAdmin;

    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    public void setSuperAdmin(SuperAdmin superAdmin) {
        this.superAdmin = superAdmin;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Executing command line runner...");
        if(StringUtils.isNotBlank(superAdmin.getEmail()) &&
                !userService.existsWithPrimaryKey(superAdmin.getEmail())){
            User user = new User();
            user.setAccountNumber(superAdmin.getEmail());
            if(StringUtils.isBlank(superAdmin.getUserName())){
                superAdmin.setUserName(DEFAULT_USERNAME);
            }
            user.setUserName(superAdmin.getUserName());
            if(StringUtils.isBlank(superAdmin.getPassword())){
                superAdmin.setPassword(DEFAULT_PASSWORD);
            }
            String encodePassword = SaSecureUtil.md5BySalt(superAdmin.getPassword(), superAdmin.getEmail());
            user.setPassword(encodePassword);
            user.setUserState(true);
            user.setStatus(UserStatusEnum.ADMIN);
            userService.insertData(user);
            userRoleService.deleteById(user.getUserId());
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getUserId());
            userRole.setRoleId(1);
            userRoleService.insertData(userRole);
        }
    }
}
