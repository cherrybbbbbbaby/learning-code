package com.djh.demo.juc;

import java.util.concurrent.*;

public class ThreadPoolTest {

    public static void main(String[] args) throws Exception {

        ExecutorService pool1 = Executors.newFixedThreadPool(1);//Executors创建的线程池为无界队列

        ThreadPoolExecutor pool2 = new ThreadPoolExecutor(
                2,
                4,
                5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(10),
                Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        //自定义拒绝策略

                    }
                });

        ThreadPoolTest threadPoolTest = new ThreadPoolTest();
        threadPoolTest.demo1(pool1);
        threadPoolTest.demo1(pool2);

    }


    public void demo1(ExecutorService pool){
        FutureTask<String> f = new FutureTask<String>(new Callable<String>() {
            public String call() throws Exception {
                Thread.sleep(5000);
                return "finish";
            }
        });
        pool.execute(f);
        String s = null;
        try {
            s = f.get(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            //当前线程被中断
            e.printStackTrace();
        } catch (ExecutionException e) {
            //任务执行过程中出现异常
            e.printStackTrace();
        } catch (TimeoutException e) {
            //超时
            e.printStackTrace();
        }
        System.out.println(s);
        pool.shutdown();
    }

}
