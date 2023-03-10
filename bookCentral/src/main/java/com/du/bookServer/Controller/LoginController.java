package com.du.bookServer.Controller;


import com.du.bookServer.Service.LoginService;
import com.du.bookServer.domain.ResponseResult;
import com.du.bookServer.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping ("/user/login")
    public ResponseResult login(@RequestBody User user) {
        // login in
        return loginService.login(user);

    }
    @RequestMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }

    @RequestMapping("/user/info")
    public ResponseResult getInfo(){
        return loginService.getInfo();
    }


}
