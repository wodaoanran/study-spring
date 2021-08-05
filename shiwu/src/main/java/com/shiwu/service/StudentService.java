package com.shiwu.service;

import com.shiwu.pojo.Student;

import java.util.List;

/**
 * @author OVAmach
 * @date 2021/8/4
 */
public interface StudentService {
    Student save(Student student);
    List<Student> saveAll(List<Student> studentList);
}
