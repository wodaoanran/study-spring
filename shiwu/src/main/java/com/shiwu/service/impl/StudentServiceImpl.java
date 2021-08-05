package com.shiwu.service.impl;

import com.shiwu.pojo.Student;
import com.shiwu.repository.StudentRepository;
import com.shiwu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author OVAmach
 * @date 2021/8/4
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public Student save(Student student) {
        try {
            Student save = studentRepository.save(student);
            return save;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    /**
     * @Transactional是添加一个事务管理
     * 1. 使用场景：批量添加数据时，保证数据如果一条添加失败，直接回滚数据
     * 2. 维护数据的唯一性
     * 3. 被try catch捕获的异常不会回滚
     */
    public List<Student> saveAll(List<Student> studentList){
        List<Student> students = studentRepository.saveAll(studentList);
        return students;
    }
}
