package com.gisquest.plan.service.security;

import com.gisquest.plan.service.model.User;
import com.gisquest.plan.service.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author honght
 * @date 2020/9/27 10:13
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.getUserByName(userName);
        if(user == null){
            throw new UsernameNotFoundException("用户" + userName + "不存在!");
        }
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),new ArrayList<SimpleGrantedAuthority>());
        return userDetails;
    }
}
