package com.example.threaddemo.threadpool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author dobest
 * @ClassName: FixedThreadPoolDemo
 * @Description: shutdownNow 方法验证
 * @date 2019/5/6 - 10:19
 */
public class FixedThreadPoolDemo2 {

    static class ThreadDemo implements Runnable{

        private String threadName;

        public ThreadDemo(String name){
            threadName = name;
        }
        @Override
        public void run() {
            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            System.out.println("当前线程运行的是："+ threadName + ",开始时间为："+dateFormat.format(date));

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Date date1 = new Date();
            System.out.println("-----当前线程运行的是："+ threadName + ",结束时间为："+dateFormat.format(date1));
        }
    }

    public static void main(String []args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        for (int i=0;i<20;i++){
            threadPoolExecutor.execute(new ThreadDemo("task-"+i));
        }
        Thread.sleep(6000);
        System.out.println("开始执行shutdownNow");
        threadPoolExecutor.shutdownNow();
    }
}
