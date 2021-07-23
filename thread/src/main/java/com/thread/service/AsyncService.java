package com.thread.service;

import com.thread.pojo.Similar;
import org.springframework.data.domain.Page;

public interface AsyncService {
    /**
     * 执行异步任务
     * 可以根据需求，自己加参数拟定，我这里就做个测试演示
     * @return
     */
    Page<Similar> executeAsync(int page, int size);
}