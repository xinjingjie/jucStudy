package juc.lock.reentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author： xinjingjie
 * @Date：2021/2/26 15:15
 **/
public class TestInterrupt {
    static final ReentrantLock reentrantLock = new ReentrantLock();
    private static boolean isUnlock = true;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> t1(), "thread1");
        Thread thread2 = new Thread(() -> t2(), "thread2");
        thread1.start();
        thread2.start();

        thread2.interrupt();
        thread1.join();
        thread1.interrupt();
    }

    static void t1() {
        try {
            reentrantLock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().isInterrupted());
            System.out.println("thread1 is executing");
        } catch (InterruptedException e) {
            System.out.println("catch error:interrupted!");
            isUnlock = false;
        } finally {
            if (isUnlock) {
                reentrantLock.unlock();
            }
        }

    }

    static void t2() {
        reentrantLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().isInterrupted());
            System.out.println("thread2 is executing");
        } finally {
            reentrantLock.unlock();
        }

    }
}
