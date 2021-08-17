package thread.damonThread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 守护线程在其他非守护线程全部退出的情况下不继续执行
 *
 * @Author： xinjingjie
 * @Date：2021/5/31 11:02
 **/
public class DamonThreadDemo {
    static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                add(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread1");
        Thread t2 = new Thread(() -> {
            try {
                add(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread2");

        t1.setDaemon(true);
        t2.setDaemon(false);
        t1.start();
        t2.start();
        countDownLatch.countDown();
    }

    public static void add(int n) throws InterruptedException {
        countDownLatch.await();
        int i = 0;
        while (i < n) {
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println((!Thread.currentThread().isDaemon() ? "非守护线程" : "守护线程") + Thread.currentThread().getName() + "---" + i);
            i++;
        }
    }
}
