package com.datajpa.repository;

import com.datajpa.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author OVAmach
 * @date 2021/8/26
 */
public interface StudentRepository extends JpaRepository<Student,Long> {
}
