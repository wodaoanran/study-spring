package com.aop.respository;

import com.aop.pojo.SystemLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author OVAmach
 * @date 2021/7/8
 */
public interface SystemLogRepository extends JpaRepository<SystemLog,Integer> {
}
