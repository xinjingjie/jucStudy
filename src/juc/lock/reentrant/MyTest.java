package juc.lock.reentrant;

import org.junit.Test;

/**
 * @Author： xinjingjie
 * @Date：2021/2/24 14:21
 * 测试可重入锁和不可重入锁
 **/
public class MyTest {

    MyLock myLock = new MyLock();
    MyReentrantLock myReentrantLock = new MyReentrantLock();

    @Test
    public void dd() throws InterruptedException {
        child();
    }


    public void child() throws InterruptedException {
//        myLock.lock();
        myReentrantLock.lock();
        try {
            System.out.println("child child ");
            child2();
        } finally {
//            myLock.unlock();
            myReentrantLock.unlock();
        }
    }

    public void child2() throws InterruptedException {
//        myLock.lock();
        myReentrantLock.lock();
        try {
            System.out.println("child2 child2 ");
//            child2();
        } finally {
//            myLock.unlock();
            myReentrantLock.unlock();
        }
    }
}
