package com.shiwu;

import com.shiwu.pojo.Student;
import com.shiwu.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ShiwuApplicationTests {
    @Autowired
    private StudentService studentService;

    @Test
    void contextLoads() {
        List<Student> studentList = new ArrayList<>();
        Student student = new Student();
        student.setAge(45);
        student.setName("xia");
        student.setSex("女");
        Student student1 = new Student();
        student1.setAge(45);
        student1.setName("xaiyun");
        student1.setSex("女");
        studentList.add(student);
        studentList.add(student1);
        try {
            studentService.saveAll(studentList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
