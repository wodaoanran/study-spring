package com.aop.common.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author OVAmach
 * @date 2021/7/14
 */

/**
 * 1.@Target表示该注解用于什么地方
 * 2.@Retention保留到运行时
 * 3.@Documented可以生成文档
 */
@Target({PARAMETER,METHOD})
@Retention(RUNTIME)
@Documented
public @interface Log {
    String method() default "";
}
