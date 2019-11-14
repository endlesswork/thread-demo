package com.example.threaddemo.threadpool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author dobest
 * @ClassName: shutdown方法验证
 * @Description: TODO
 * @date 2019/5/6 - 10:19
 */
public class FixedThreadPoolDemo {

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

    public static void main(String []args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor =  new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        for (int i=0;i<20;i++){
            threadPoolExecutor.execute(new ThreadDemo("task-"+i));
        }
        Thread.sleep(5000);
        System.out.println("开始执行shutdown");
        threadPoolExecutor.shutdown();
    }
}
