package com.aop.service.impl;

import com.aop.pojo.SystemLog;
import com.aop.respository.SystemLogRepository;
import com.aop.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (TSystemLog)表服务实现类
 *
 * @author makejava
 * @since 2021-07-08 17:54:49
 */
@Service
public class SystemLogServiceImpl implements SystemLogService {
    @Autowired
    private SystemLogRepository systemLogRepository;
    @Override
    public SystemLog save(SystemLog systemLog) {
        return systemLogRepository.save(systemLog);
    }
}