package com.aop.common.aspect;

import com.aop.common.utils.JWTUtil;
import com.aop.pojo.SystemLog;
import com.aop.pojo.User;
import com.aop.respository.RoleRepository;
import com.aop.respository.SystemLogRepository;
import com.aop.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * @author OVAmach
 * @date 2021/7/8
 */
@Aspect
@Component
public class LogAspect {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private SystemLogRepository systemLogRepository;
    //切入点设置成一个注解
    @Pointcut("@annotation(com.aop.common.aspect.Log)")
    public void controllerAspect() {
    }

   /* *//**
     * 前置通知
     * @param joinPoint
     *//*
    @Before("controllerAspect()")
    public void deBefore(JoinPoint joinPoint){
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        System.out.println("===========前置通知=========");
        System.out.println("获取访问路径 : " + request.getRequestURL().toString());
        System.out.println("获取请求方式 : " + request.getMethod());
        System.out.println("获取ip : " + request.getRemoteAddr());
        System.out.println("获取方法路径 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        System.out.println("获取参数 : " + Arrays.toString(joinPoint.getArgs()));

    }*/

   /* *//**
     * 后置通知
     * @param ret
     * @throws Throwable
     *//*
    @AfterReturning(returning = "ret", pointcut = "controllerAspect()")
    public void doAfterReturning(Object ret){
        // 处理完请求，返回内容
        System.out.println("======方法的返回值======");
        System.out.println("方法的返回值 : " + ret);
    }*/

  /*  *//**
     * 后置异常通知
     * @param jp
     *//*
    @AfterThrowing("controllerAspect()")
    public void throwss(JoinPoint jp){
        System.out.println("方法异常时执行.....");
    }
*/

    /**
     * 环绕通知，以上通知都可以实现
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //TODO: 创建一个日志实体对象
        SystemLog log = new SystemLog();
        //记录时间
        long start = System.currentTimeMillis();
        //TODO:拦截的实体类,就是当前正在执行的controller
        Object target = pjp.getTarget();
        //TODO:获取正在执行的方法的名字
        String methodName = pjp.getSignature().getName();
        System.out.println("=======获取正在执行的方法的名字=======");
        System.out.println(methodName);
        //TODO：获取正在执行的方法的参数
        Object[] args = pjp.getArgs();
        System.out.println("=======获取到的参数======");
        System.out.println(Arrays.asList(args).toString());
        //TODO: 获取拦截的参数的参数类型
        System.out.println("=======获取拦截的参数的参数类型=======");
        Signature sig = pjp.getSignature();
        System.out.println(sig.toString());
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        System.out.println("======msig======");
        System.out.println(msig.toString());
        Class[] parameterTypes = msig.getMethod().getParameterTypes();

        Object object = null;
        // 获得被拦截的方法
        Method method = null;
        try {
            method = target.getClass().getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        System.out.println(method);
        if (null != method) {
            // 判断是否包含自定义的注解
            if (method.isAnnotationPresent(Log.class)) {
                Log annotation = method.getAnnotation(Log.class);
                log.setMethod(annotation.method());
                try {
                    object = pjp.proceed();
                    //获取登录用户账户
                    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                    //拿到当前用户
                    String authorization = request.getHeader("Authorization");
                    User user = userService.findByUserName(JWTUtil.getUsername(authorization));
                    //设置当前用户名
                    if (Objects.nonNull(user)){
                        String name = user.getUserName();
                        log.setRoleName(roleRepository.getRoleByUserId(user.getId()));
                        log.setOperUser(name);
                    }
                    //获取系统时间
                    log.setOperTime(new Date());

                    long end = System.currentTimeMillis();
                    log.setResponseTime(""+(end-start));
                    log.setResult("执行成功");
                    systemLogRepository.save(log);
                    System.out.println(log);
                } catch (Throwable e) {
                    // TODO Auto-generated catch block
                    long end = System.currentTimeMillis();
                    log.setResponseTime(""+(end-start));
                    log.setResult("执行失败");
                    systemLogRepository.save(log);
                }
            } else {//没有包含注解
                object = pjp.proceed();
            }
        } else { //不需要拦截直接执行
            object = pjp.proceed();
        }
        return object;
    }
}
