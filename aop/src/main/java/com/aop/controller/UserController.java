package com.aop.controller;

import com.aop.common.aspect.Log;
import com.aop.pojo.User;
import com.aop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author OVAmach
 * @date 2021/7/8
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Log(method = "查找全部用户")
    @RequestMapping(value = "/findUserAll",method = RequestMethod.GET)
    public List<User> findUserAll() {
        List<User> userList = userService.findAll();
        return userList;
    }
}
