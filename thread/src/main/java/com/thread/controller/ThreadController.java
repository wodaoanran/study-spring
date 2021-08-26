package com.thread.controller;

import com.thread.pojo.Student;
import com.thread.repository.StudentRepository;
import com.thread.service.AsyncService;
import com.utils.R;
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
public class ThreadController {

    @Autowired
    private AsyncService asyncService;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/asyncStu")
    public R asyncStu(Integer page, Integer size) {
        try {
            if (page == null) {
                page = 0;
            }
            if (size == null) {
                int count = (int) studentRepository.count();
                size = count;
            }
            Page<Student> students = asyncService.studentAsync(page, size);
            System.out.println(students.toString());
            Thread.sleep(10000);
            return R.ok("students", students);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

}
