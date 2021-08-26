package com.interceptor.config;

import com.interceptor.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author OVAmach
 * @date 2021/8/23
 * 过滤器
 */
@Component
public class MyFilter implements Filter {
    @Autowired
    private TestService testService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filter 处理中");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器进行初始化");
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }
}
