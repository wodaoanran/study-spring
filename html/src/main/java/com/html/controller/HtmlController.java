package com.html.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author OVAmach
 * @date 2021/8/13
 */
@Controller
@RequestMapping("/html")
public class HtmlController {

    @GetMapping("/main")
    public String getMain(){
        return "main";
    }
}
