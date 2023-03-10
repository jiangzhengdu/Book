package com.du.bookServer.Service.impl;


import com.du.bookServer.Service.LoginService;
import com.du.bookServer.domain.LoginUser;
import com.du.bookServer.domain.ResponseResult;
import com.du.bookServer.domain.User;
import com.du.bookServer.mapper.UserMapper;
import com.du.bookServer.utils.JwtUtil;
import com.du.bookServer.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    RedisCache redisCache;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMapper userMapper;
    @Override
    public ResponseResult login(User user) {
        // use AuthenticationManager authenticate

        // if the authenticate fail

        // save user information into redis userid as key

        //使用authenticationManager的authenticate方法进行用户认证，需要在SecurityConfig中配置把authenticationManager注入容器
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
//            return new ResponseResult(200, "login fail!");
            throw new RuntimeException("login failed!");
        }

        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userid = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        // if authenticate success then use userID to generate a JWT, save JWT into ResponseResult
        redisCache.setCacheObject("login:" + userid, loginUser);

        return new ResponseResult(200, "login success", map);

    }

    @Override
    public ResponseResult logout() {
        //获取 securityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getUser().getId();
        redisCache.deleteObject("login:" + id);
        return new ResponseResult(200, "logout success");
    }

    @Override
    //
    public ResponseResult save() {
        return null;
    }

    @Override
    public ResponseResult getInfo() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getUser().getId();
        User user = userMapper.queryById(id.intValue());
        user.setPassword("password not show!");
        return new ResponseResult(200, "Get user Info success!", user);
    }
}
