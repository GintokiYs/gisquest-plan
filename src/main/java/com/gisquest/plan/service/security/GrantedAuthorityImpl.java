package com.gisquest.plan.service.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author honght
 * @date 2020/9/27 10:13
 */
public class GrantedAuthorityImpl implements GrantedAuthority {

    private String authority;

    public GrantedAuthorityImpl(String authority){
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
