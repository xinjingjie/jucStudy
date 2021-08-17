package juc.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author： xinjingjie
 * @Date：2021/2/27 16:10
 **/
public class Test2 {
    static ArrayBlockingQueue a = new ArrayBlockingQueue<>(500);
    static volatile ArrayBlockingQueue<String> data = new ArrayBlockingQueue<>(10);

    static volatile boolean flag = false;

    public static void main(String[] args) {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(20, 40, 5, TimeUnit.SECONDS, a, new ThreadPoolExecutor.CallerRunsPolicy());

        executorService.allowCoreThreadTimeOut(true);
        for (int i = 0; i < 500; i++) {

            executorService.submit(new SendThread2(i + ""));

//            if (i % 2 == 0) {
//                executorService.submit(new SendThread2(i + ""));
//            }else{
//                executorService.submit(new SendThread());
//            }

//            }
        }
        executorService.shutdown();
    }

    static class SendThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < data.size(); i++) {
                String poll = data.poll();
                if (poll != null) {
                    System.out.println(poll);
                } else {
                    break;
                }
            }
        }
    }


    static class SendThread2 implements Runnable {
        private String name;

        SendThread2(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "---" + name);
        }
    }
}
