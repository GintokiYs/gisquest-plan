package com.gisquest.plan.service.dao;


import com.gisquest.plan.service.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author honght
 * @date 2020/9/27 10:13
 */
@Repository
public interface UserMapper {
    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    User getUserByName(String userName);
}
