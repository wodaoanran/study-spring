package com.thread.controller;

import com.thread.pojo.Similar;
import com.thread.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author OVAmach
 * @date 2021/7/16
 */
@RestController
@RequestMapping("/similar")
public class SimilarController {
    @Autowired
    private AsyncService asyncService;

    @GetMapping("/async")
    public Page<Similar> async(int page, int size){
        Long startTime = System.currentTimeMillis();
        Page<Similar> similars = asyncService.executeAsync(page,size);
        Long endTime = System.currentTimeMillis();
        Long time = endTime - startTime;
        System.out.println(time);
        return similars;
    }
}
