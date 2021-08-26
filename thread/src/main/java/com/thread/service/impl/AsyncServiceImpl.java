package com.thread.service.impl;

import com.thread.pojo.Student;
import com.thread.repository.StudentRepository;
import com.thread.service.AsyncService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class AsyncServiceImpl implements AsyncService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Async
    public Page<Student> studentAsync(int page, int size) {
        log.info("start executeAsync");
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<Student> all = studentRepository.findAll(pageRequest);
        log.info("end executeAsync");
        return all;
    }
}