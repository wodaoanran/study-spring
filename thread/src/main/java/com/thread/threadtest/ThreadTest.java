package com.thread.threadtest;

import java.util.Date;

/**
 * @author OVAmach
 * @date 2021/6/16
 */
public class ThreadTest {
    // 顺序编程 吃喝示例：当吃饭吃不完的时候，是不能喝酒的，只能吃完晚才能喝酒
    public static void main(String[] args) throws Exception {
        // 先吃饭再喝酒
        /*eat();
        drink();*/
        new Thread(() -> {
            System.out.println("开始吃饭?...\t" + new Date());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("结束吃饭?...\t" + new Date());
        }).start();

        new Thread(()->{
            System.out.println("开始喝酒?️...\t" + new Date());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("结束喝酒?...\t" + new Date());
        }).start();


    }

    private static void eat() throws Exception {

    }

    private static void drink() throws Exception {
        System.out.println("开始喝酒?️...\t" + new Date());
        Thread.sleep(5000);
        System.out.println("结束喝酒?...\t" + new Date());
    }

}
