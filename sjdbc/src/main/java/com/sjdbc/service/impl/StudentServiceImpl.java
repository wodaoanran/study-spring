package com.sjdbc.service.impl;

import com.sjdbc.pojo.Student;
import com.sjdbc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author OVAmach
 * @date 2021/8/13
 */
public class StudentServiceImpl implements StudentService{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Student student) {
        String sql="insert into student (name,age)values(?,?)";
        int row = jdbcTemplate.update(sql, "李四", 12);
        return row;
    }
}
