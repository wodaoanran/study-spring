package com.interceptor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author OVAmach
 * @date 2021/8/23
 */
@RestController
public class Test1Controller {
    @GetMapping("/test")
    public String test1(){
        System.out.println("执行");
        return "test1()";
    }
}
