package com.stardata.observ.service;

import javax.servlet.http.HttpServletRequest;

import com.stardata.observ.model.pg.User;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User getLoginUser(HttpServletRequest request) {
        // todo 真正从请求中获取当前登录用户
        User user = new User();
        user.setId("1");
        return user;
    }
}
