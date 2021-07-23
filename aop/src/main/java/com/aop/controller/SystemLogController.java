package com.aop.controller;

import com.aop.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (TSystemLog)表控制层
 *
 * @author makejava
 * @since 2021-07-08 17:54:49
 */
@RestController
@RequestMapping("/systemLog")
public class SystemLogController {
    /**
     * 服务对象
     */
    @Autowired
    private SystemLogService systemLogService;


}