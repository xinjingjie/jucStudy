package thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author： xinjingjie
 * @Date：2021/2/25 10:33
 **/
public class ThreadDemo {
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> test());

        //此时如果thread1先抢占到资源，就不会卡死，但是main程序先抢占的话就会死锁
        thread1.start();

        //这里睡眠100millis，thread1抢占的几率变大
        System.out.println("i am gonna sleep");
        Thread.sleep(100);

        long start = System.currentTimeMillis();
        //不同线程是阻塞的，不可重入
        lock.lock();
        try {
            //此时join 会让thread1先执行，而thread1执行需要锁，获取不到锁，造成死锁
            //如果thread1已经执行完了，再join就不会生效了
            thread1.join();
            Thread.sleep(5000);
        } finally {
            lock.unlock();
        }
        System.out.println("i will print after thread1");
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void test() {
//        Thread.yield();
        System.out.println("i am in thread1");
        lock.lock();
        try {
            for (int i = 0; i < 20; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

