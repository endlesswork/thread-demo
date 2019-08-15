package com.example.threaddemo.threadpool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author dobest
 * @ClassName: FixedThreadPoolDemo
 * @Description: TODO
 * @date 2019/5/6 - 10:19
 */
public class FixedThreadPoolDemo3 {

    static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

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
        }
    }

    static class AddThread implements Runnable{
        @Override
        public void run() {
            //往线程池添加任务进入
            for (int i=0;i<20;i++){
                threadPoolExecutor.execute(new ThreadDemo("task-"+i));
                System.out.println("-------------添加task-"+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String []args) throws InterruptedException {
        Thread thread = new Thread(new AddThread());
        thread.start();
        Thread.sleep(8000);
        System.out.println("开始执行shutdown");
        threadPoolExecutor.shutdown();
    }
}
