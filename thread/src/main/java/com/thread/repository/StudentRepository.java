package com.thread.repository;

import com.thread.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author OVAmach
 * @date 2021/8/9
 */
public interface StudentRepository extends JpaRepository<Student,Long> {
}
