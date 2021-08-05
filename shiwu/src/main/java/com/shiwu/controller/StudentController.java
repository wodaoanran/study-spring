package com.shiwu.controller;

import com.shiwu.pojo.Student;
import com.shiwu.service.StudentService;
import com.shiwu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author OVAmach
 * @date 2021/8/4
 */
@RestController
@RequestMapping(name = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping(name = "/saveStudent")
    public R saveStudent(Student student){
        return R.ok("student",studentService.save(student));
    }
}
