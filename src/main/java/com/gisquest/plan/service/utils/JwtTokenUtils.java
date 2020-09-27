package com.gisquest.plan.service.utils;

import com.gisquest.plan.service.security.GrantedAuthorityImpl;
import com.gisquest.plan.service.security.JwtAuthenticatioToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;

/**
 * @author honght
 * @date 2020/9/27 10:13
 */
public class JwtTokenUtils implements Serializable {

    private static final long serialVersionUID = -7320677026905591409L;

    /**
     * 用户名称
     */
    private static final String USERNAME = Claims.SUBJECT;
    /**
     * 创建时间
     */
    private static final String CREATED = "created";
    /**
     * 权限列表
     */
    private static final String AUTHORITIES = "authorities";
    /**
     * 密钥
     */
    private static final String SECRET = "abcdefgh";
    /**
     * 有效期12小时
     */
    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000;

    /**
     * 生成令牌
     * @param authentication
     * @return
     */
    public static String generateToken(Authentication authentication) {
        Map<String, Object> claims = new HashMap<>(3);
        claims.put(USERNAME, SecurityUtils.getUsername(authentication));
        claims.put(CREATED, new Date());
        claims.put(AUTHORITIES, authentication.getAuthorities());
        return generateToken(claims);
    }

    private static String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    /**
     * 从令牌中获取用户名
     * @param token
     * @return
     */
    public static String getUsernameFromToken(String token){
        Claims claims = getClaimsFromToken(token);
        String username = claims.getSubject();
        return username;
    }

    /**
     * 从令牌中获取数据申明
     * @param token
     * @return
     */
    private static Claims getClaimsFromToken(String token){
        Claims claims  = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        return claims;
    }

    /**
     * 获取请求中的token
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if("".equals(token)){
            token = null;
        }
        return token;
    }

    /**
     * 校验令牌
     * @param token
     * @param username
     * @return
     */
    public static Boolean validateToken(String token,String username){
        String claimUsername = getUsernameFromToken(token);
        return (claimUsername.equals(username) && !isTokenExpired(token));
    }

    /**
     * 判断令牌是否过期
     * @param token
     * @return
     */
    public static boolean isTokenExpired(String token) {
        Claims claims = getClaimsFromToken(token);
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    /**
     * 刷新令牌
     * @param token
     * @return
     */
    public static String refreshToken(String token){
        Claims claims = getClaimsFromToken(token);
        claims.put(CREATED,new Date());
        return generateToken(claims);
    }

    public static Authentication getAuthenticationeFromToken(HttpServletRequest request) {
        Authentication authentication = null;
        // 获取请求携带的令牌
        String token = JwtTokenUtils.getToken(request);
        if(token != null) {
            // 请求令牌不能为空
            if(SecurityUtils.getAuthentication() == null) {
                // 上下文中Authentication为空
                Claims claims = getClaimsFromToken(token);
                if(claims == null) {
                    return null;
                }
                String username = claims.getSubject();
                if(username == null) {
                    return null;
                }
                if(isTokenExpired(token)) {
                    return null;
                }
                Object authors = claims.get(AUTHORITIES);
                List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                if (authors != null && authors instanceof List) {
                    for (Object object : (List) authors) {
                        authorities.add(new GrantedAuthorityImpl((String) ((Map) object).get("authority")));
                    }
                }
                authentication = new JwtAuthenticatioToken(username, null, authorities, token);
            } else {
                if(validateToken(token, SecurityUtils.getUsername())) {
                    // 如果上下文中Authentication非空，且请求令牌合法，直接返回当前登录认证信息
                    authentication = SecurityUtils.getAuthentication();
                }
            }
        }
        return authentication;
    }
}
