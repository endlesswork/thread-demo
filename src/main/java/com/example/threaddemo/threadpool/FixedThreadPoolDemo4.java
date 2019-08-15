package com.example.threaddemo.threadpool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.example.threaddemo.util.ThreadPoolExecutor;

/**
 * @author dobest
 * @ClassName: FixedThreadPoolDemo
 * @Description: TODO
 * @date 2019/5/6 - 10:19
 */
public class FixedThreadPoolDemo4 {

    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

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
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Date date1 = new Date();
            System.out.println("-----当前线程运行的是："+ threadName + ",结束时间为："+dateFormat.format(date1));
        }
    }

    static class AddThread implements Runnable{
        @Override
        public void run() {
            //往线程池添加任务进入
            for (int i=0;i<20;i++){
                System.out.println("-------------添加task-"+i+"开始");
                threadPoolExecutor.execute(new ThreadDemo("task-"+i));
                System.out.println("-------------添加task-"+i+"结束");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
             }
        }
    }

    static class UpdateThread implements Runnable{

        @Override
        public void run() {
            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            System.out.println("执行线程池关闭开始时间："+dateFormat.format(date));
            threadPoolExecutor.shutdown();
            Date date1 = new Date();
            System.out.println("执行线程池关闭关闭时间："+dateFormat.format(date));
        }
    }
    public static void main(String []args) throws InterruptedException {
        Thread thread = new Thread(new AddThread());
        thread.start();
        Thread.sleep(6000);
        Thread thread1 = new Thread(new UpdateThread());
        thread1.start();
    }
}
