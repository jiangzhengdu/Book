package com.du.bookServer.Service;


import com.du.bookServer.domain.ResponseResult;
import com.du.bookServer.domain.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();

    ResponseResult save();

    ResponseResult getInfo();
}
