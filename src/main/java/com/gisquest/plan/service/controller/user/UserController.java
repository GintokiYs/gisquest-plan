package com.gisquest.plan.service.controller.user;

import com.gisquest.plan.service.security.JwtAuthenticatioToken;
import com.gisquest.plan.service.utils.SecurityUtils;
import com.gisquest.plan.service.vo.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author honght
 * @date 2020/9/27 10:13
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;


    @ApiOperation("登录方法")
    @PostMapping("/login")
    public ResponseResult login(@RequestParam String username, @RequestParam String password, HttpServletRequest request){
        JwtAuthenticatioToken jwtAuthenticatioToken = SecurityUtils.login(request, username, password, authenticationManager);
        return ResponseResult.ok(jwtAuthenticatioToken.getToken());
    }

    @GetMapping(value = "/test")
    @ApiOperation(value = "我是一个测试方法")
    public ResponseResult<String> test(){
        return ResponseResult.ok("Wo是一个测试方法");
    }
}
