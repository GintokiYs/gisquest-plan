package com.gisquest.plan.service.service.user.impl;

import com.gisquest.plan.service.dao.UserMapper;
import com.gisquest.plan.service.model.User;
import com.gisquest.plan.service.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author liul
 * @Date 2020/7/11 14:08
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByName(String userName) {
        return userMapper.getUserByName(userName);
    }
}
