package juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author： xinjingjie
 * @Date：2021/2/24 10:50
 * 可重入锁的 公平锁的特性
 * 将所有线程依次放入CLH同步队列，然后再从队列中依次取出来执行
 **/
public class LockDemo {
    static ReentrantLock reentrantLock;

    public static void main(String[] args) {
        reentrantLock = new ReentrantLock(true);
        new Thread(() -> test()).start();
        new Thread(() -> test()).start();
        new Thread(() -> test()).start();
    }

    public static void test() {
        for (int i = 0; i < 2; i++) {
            reentrantLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "hello");
                System.out.println((reentrantLock.hasQueuedThreads()));
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }
    }
}
