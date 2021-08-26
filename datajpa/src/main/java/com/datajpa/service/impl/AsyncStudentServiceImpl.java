package com.datajpa.service.impl;

import com.datajpa.pojo.Student;
import com.datajpa.service.AsyncStudentService;
import com.datajpa.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author OVAmach
 * @date 2021/8/26
 */
@Service
@Slf4j
public class AsyncStudentServiceImpl implements AsyncStudentService {
    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync(List<Student> students, StudentService studentService, CountDownLatch countDownLatch) {
        log.warn("start executor");
        try {
            studentService.saveBatch(students);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            countDownLatch.countDown();
        }
        log.warn("end executor");

    }
}
