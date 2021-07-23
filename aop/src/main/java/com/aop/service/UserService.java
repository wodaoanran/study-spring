package com.aop.service;

import com.aop.pojo.User;
import java.util.List;

/**
 * @author OVAmach
 * @date 2021/7/8
 */
public interface UserService {
    List<User> findAll();

    User save(User user);

    User findByUserName(String userName);
}
