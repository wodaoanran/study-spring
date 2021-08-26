package com.datajpa;

import com.datajpa.pojo.Student;
import com.datajpa.repository.StudentRepository;
import com.datajpa.service.AsyncStudentService;
import com.datajpa.service.StudentService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@SpringBootTest
@Slf4j
class DatajpaApplicationTests {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private AsyncStudentService asyncStudentService;

    @Test
    void contextLoads() throws InterruptedException, CloneNotSupportedException {
        List<Student> list = studentRepository.findAll();
        list.parallelStream().forEach(student -> {
            int index = list.indexOf(student);
            List<Student> students = list.subList(index+1, list.size());
            System.out.println("截取前的长度："+list.size()+", 截取后的长度："+students.size());
            log.info(Thread.currentThread().getName());
        });

    }

    @Test
    void saveBatch() throws CloneNotSupportedException, InterruptedException {
        Student stu = new Student();
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Student student = (Student) stu.clone();
            student.setSex("0");
            student.setName("自定义批量添加" + i);
            list.add(student);
        }
        //测试每100条数据插入开一个线程
        List<List<Student>> lists = Lists.partition(list, 100);
        CountDownLatch countDownLatch = new CountDownLatch(lists.size());
        for (List<Student> students : lists) {
            asyncStudentService.executeAsync(students, studentService, countDownLatch);
            Thread.sleep(20);
            log.info(Thread.currentThread().getName());
        }
        try {
            countDownLatch.await(); //保证之前的所有的线程都执行完成，才会走下面的；
            // 这样就可以在下面拿到所有线程执行完的集合结果
        } catch (Exception e) {
            log.error("阻塞异常:" + e.getMessage());
        }
    }

}
