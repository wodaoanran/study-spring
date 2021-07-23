package com.aop.service;

import com.aop.pojo.SystemLog;

/**
 * (TSystemLog)表服务接口
 *
 * @author makejava
 * @since 2021-07-08 17:54:48
 */
public interface SystemLogService {
  SystemLog save(SystemLog systemLog);
}