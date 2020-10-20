package com.djh.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        for (int i = 0; i <2; i++) {
            Runnable r = new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("-1");
                    countDownLatch.countDown();
                }
            };
            threadPool.execute(r);
        }


        countDownLatch.await();
        System.out.println("finish");
        threadPool.shutdown();
    }

}
