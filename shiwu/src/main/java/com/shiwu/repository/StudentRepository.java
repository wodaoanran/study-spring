package com.shiwu.repository;

import com.shiwu.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author OVAmach
 * @date 2021/8/4
 */
public interface StudentRepository extends JpaRepository<Student,Long> {
}
