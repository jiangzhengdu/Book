package com.du.bookServer.Service.impl;


import com.du.bookServer.domain.LoginUser;
import com.du.bookServer.domain.User;
import com.du.bookServer.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询用户信息
        User user = userMapper.queryByName(username);
        if (Objects.isNull(user)) {
            throw new RuntimeException("username or password error!");
        }


        List<String> list;
        if (user.getRole() == 0) {
            list = new ArrayList<>(Arrays.asList("admin"));
        }
        else {
            list = new ArrayList<>(Arrays.asList("normal"));
        }
        return new LoginUser(user,list);
//        List<String> list = new ArrayList<>(Arrays.asList("test", "admin"));

    }
}
