package juc.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author： xinjingjie
 * @Date：2021/3/3 14:05
 * 到达某一时刻一起执行
 **/
public class CountDownLatchDemo2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(10);
        for (int i = 0; i < 10; ++i)
            // create and start threads
            new Thread(new Worker(startSignal, doneSignal)).start();

        Thread.sleep(2000);
        System.out.println("比赛开始");
        startSignal.countDown();

        // let all threads proceed doSomethingElse();
        doneSignal.await(); // wait for all to finish
    }

    static class Worker implements Runnable {
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;

        Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "正在准备");
                startSignal.await();
                doWork();
                doneSignal.countDown();
                System.out.println(Thread.currentThread().getName() + "完成比赛");
            } catch (InterruptedException ex) {
            } // return;
        }

        void doWork() throws InterruptedException {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + "正在冲刺");
        }
    }
}
