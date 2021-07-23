package com.thread.threadtest;

import java.util.Date;

public class DrinkThread extends Thread {
    @Override
    public void run() {
        System.out.println("开始喝酒?️...\t" + new Date());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束喝酒?...\t" + new Date());
    }
}