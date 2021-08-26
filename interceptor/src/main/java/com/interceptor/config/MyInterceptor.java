package com.interceptor.config;

import com.interceptor.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author OVAmach
 * @date 2021/8/20
 * 自定义拦截器
 */
@Slf4j
@Component
public class MyInterceptor  implements HandlerInterceptor {
    @Autowired
    private TestService testService;

    /**
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     * 在请求处理之前执行，主要用于权限验证、参数过滤、数据源设置等
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle()，在请求之前执行");
        return true;
    }

    /**
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     *  当前请求进行处理之后执行，主要用于日志记录、权限检查、性能监控、通用行为等
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("MyInterceptor ==> preHandle method: do request after");
        testService.a();
        System.out.println("postHandle()，在请求进行处理之后执行");
    }

    /**
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     * 当前对应的interceptor的perHandle方法的返回值为true时,postHandle执行完成并渲染页面后执行，主要用于资源清理工作
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion(),用于资源清理工作");
    }
}
