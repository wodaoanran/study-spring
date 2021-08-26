package com.interceptor.service.impl;

import com.interceptor.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author OVAmach
 * @date 2021/8/23
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public void a() {
        System.out.println("我是方法a");
    }
}
