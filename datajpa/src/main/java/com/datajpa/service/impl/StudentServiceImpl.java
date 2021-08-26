package com.datajpa.service.impl;

import com.datajpa.pojo.Student;
import com.datajpa.repository.StudentRepository;
import com.datajpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author OVAmach
 * @date 2021/8/26
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public int saveBatch(List<Student> list) throws InterruptedException {
        if (!ObjectUtils.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                entityManager.persist(list.get(i));
                if (i!=0) {
                    if (i % 1000 == 0) {
                        entityManager.flush();
                        entityManager.clear();
                    }
                }
            }
            entityManager.flush();
            entityManager.clear();
        }
        return list.size();
    }
}
