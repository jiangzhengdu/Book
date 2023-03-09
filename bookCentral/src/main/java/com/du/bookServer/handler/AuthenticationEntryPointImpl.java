package com.du.bookServer.handler;

import com.alibaba.fastjson.JSON;

import com.du.bookServer.domain.ResponseResult;
import com.du.bookServer.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 处理异常
        ResponseResult responseResult = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "username authentication failed! Please login again!");
        String s = JSON.toJSONString(responseResult);
        WebUtils.renderString(response, s);
    }

}
