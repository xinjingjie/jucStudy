package juc.lock;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author： xinjingjie
 * @Date：2021/2/24 13:51
 **/
public class TestReentrantLock {
//    ReentrantLock lock = new ReentrantLock();

    @Test
    public void test() throws InterruptedException {
        child();
    }

    public void child() throws InterruptedException {
//        myLock.lock();
        try {
            System.out.println("child child ");
            child2();
        } finally {
//            myLock.unlock();
        }
    }

    public void child2() throws InterruptedException {
//        myLock.lock();
        try {
            System.out.println("child2 child2 ");
//            child2();
        } finally {
//            myLock.unlock();
        }
    }

//    public static void main(String[] args) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (this) {
//                    System.out.println("第1次获取锁，这个锁是：" + this);
//                    int index = 1;
//                    while (true) {
//                        synchronized (this) {
//                            System.out.println("第" + (++index) + "次获取锁，这个锁是：" + this);
//                        }
//                        if (index == 10) {
//                            break;
//                        }
//                    }
//                }
//            }
//        }).start();
//    }

//    public static void main(String[] args) {
//        ReentrantLock lock = new ReentrantLock();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    lock.lock();
//                    System.out.println("第1次获取锁，这个锁是：" + lock);
//
//                    int index = 1;
//                    while (true) {
//                        try {
//                            lock.lock();
//                            System.out.println("第" + (++index) + "次获取锁，这个锁是：" + lock);
//                            try {
//                                Thread.sleep(new Random().nextInt(200));
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            if (index == 10) {
//                                break;
//                            }
//                        } finally {
//                            lock.unlock();
//                        }
//                    }
//                } finally {
//                    lock.unlock();
//                }
//            }
//        }).start();
//    }
}
