package com.aop.respository;

import com.aop.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author OVAmach
 * @date 2021/7/16
 */
public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query(value = "select r.roleName from t_role r left join t_user_role ur on r.id = ur.role_id " +
            "left join t_user u on ur.user_id = u.id where u.id = :id ",
            nativeQuery = true)
    String getRoleByUserId(@Param("id") Integer id);
}
