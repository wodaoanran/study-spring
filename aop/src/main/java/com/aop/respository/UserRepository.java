package com.aop.respository;

import com.aop.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author OVAmach
 * @date 2021/7/8
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(@Param("userName") String userName);
}
