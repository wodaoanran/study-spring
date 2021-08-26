package com.datajpa.service;

import com.datajpa.pojo.Student;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author OVAmach
 * @date 2021/8/26
 */
public interface AsyncStudentService {
    void executeAsync(List<Student> students, StudentService studentService, CountDownLatch countDownLatch);
}
