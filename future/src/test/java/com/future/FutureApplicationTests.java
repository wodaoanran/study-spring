package com.future;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;

@SpringBootTest
class FutureApplicationTests {

    /*@Test
    void contextLoads() {
        Future future = new Future<>() {
            *//**
             * boolean cancel(boolean mayInterruptInRunning) 取消一个任务，并返回取消结果。参数表示是否中断线程。
             *//*
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            *//**
             * boolean isCancelled() 判断任务是否被取消
             * @return
             *//*
            @Override
            public boolean isCancelled() {
                return false;
            }

            *//**
             * Boolean isDone() 　　 判断当前任务是否执行完毕，包括正常执行完毕、执行异常或者任务取消。
             * @return
             *//*
            @Override
            public boolean isDone() {
                return false;
            }

            *//**
             * V get() 获取任务执行结果，任务结束之前会阻塞。
             * @return
             * @throws InterruptedException
             * @throws ExecutionException
             *//*
            @Override
            public Object get() throws InterruptedException, ExecutionException {
                return null;
            }

            *//**
             * V get(long timeout, TimeUnit unit) 在指定时间内尝试获取执行结果。若超时则抛出超时异常
             * @param timeout
             * @param unit
             * @return
             * @throws InterruptedException
             * @throws ExecutionException
             * @throws TimeoutException
             *//*
            @Override
            public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        };
    }*/
    /**
     * Callalbe和Runnable的区别
     * 1. Runnable run方法是被线程调用的，在run方法是异步执行的
     * 2. Callable的call方法，不是异步执行的，是由Future的run方法调用的
     */
    @Test
    public void test01() throws ExecutionException, InterruptedException {

        Callable<Integer> call = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("正在计算结果...");
                Thread.sleep(3000);
                return 1;
            }
        };

        FutureTask<Integer> task = new FutureTask<>(call);
        Thread thread = new Thread(task);
        thread.start();

        // do something
        System.out.println(" 干点别的...");
        Integer result = task.get();
        System.out.println("拿到的结果为：" + result);

    }

    /**
     * FutureTask实现了RunnableFuture接口，而RunnableFuture继承了Runnable和Future，也就是说FutureTask既是Runnable，也是Future。
     *
     *
     *     private volatile int state; :表示对象状态，volatile关键字保证了内存可见性
     *     private static final int NEW          = 0; //任务新建和执行中
     *     private static final int COMPLETING   = 1; //任务将要执行完毕
     *     private static final int NORMAL       = 2; //任务正常执行结束
     *     private static final int EXCEPTIONAL  = 3; //任务异常
     *     private static final int CANCELLED    = 4; //任务取消
     *     private static final int INTERRUPTING = 5; //任务线程即将被中断
     *     private static final int INTERRUPTED  = 6; //任务线程已中断
     */

//    public void test02(){
//        FutureTask<Integer> futureTask = new FutureTask<>();
//    }


}
