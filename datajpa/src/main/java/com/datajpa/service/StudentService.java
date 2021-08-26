package com.datajpa.service;

import com.datajpa.pojo.Student;

import java.util.List;

/**
 * @author OVAmach
 * @date 2021/8/26
 */
public interface StudentService {
    int saveBatch(List<Student> list) throws InterruptedException;
}
