package com.interceptor.controller;

import com.interceptor.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author OVAmach
 * @date 2021/8/23
 */
@RestController
public class TestController {
    @Autowired
    private TestService testService;
    @GetMapping("/test1")
    public List<String> test1() {
        System.out.println("我是controller");
        testService.a();
        return null;
    }
}
