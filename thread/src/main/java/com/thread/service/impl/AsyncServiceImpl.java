package com.thread.service.impl;

import com.thread.pojo.Similar;
import com.thread.repository.SimilarRepository;
import com.thread.service.AsyncService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@EnableAsync
public class AsyncServiceImpl implements AsyncService {

    @Autowired
    private SimilarRepository similarRepository;

    @Override
    @Async("asyncServiceExecutor")
    public Page<Similar> executeAsync(int page, int size) {
        log.info("start executeAsync");
        System.out.println("异步线程要做的事情");
        Page<Similar> all;
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(page+1,size,sort);
        synchronized(AsyncServiceImpl.class) {
            all = similarRepository.findAll(pageRequest);
        }

        System.out.println("可以在这里执行批量插入等耗时的事情");
        log.info("end executeAsync");
        return all;
    }
}