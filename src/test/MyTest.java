package test;

import org.junit.Test;
import sun.misc.Unsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author： xinjingjie
 * @Date：2021/3/5 19:19
 **/
public class MyTest {
    public static void main(String[] args) {
//        CountDownLatch countDownLatch = new CountDownLatch(1);
        ReentrantLock lock = new ReentrantLock();
        int a = 0;
        for (int i = 0; i < 3; i++) {
            new Thread(() -> test(lock, a)).start();
        }
    }

    static void test(ReentrantLock lock, int i) {

//        try {
            while (i <= 36) {

//                for (int j = 0; j < 3; j++) {
                    System.out.println(Thread.currentThread().getName() + "--" + i++);
//                }
            }
//        } finally {
//            lock.unlock();
//        }
    }

    @Test(expected = SecurityException.class)
    public void testSingletonGetter() throws Exception {
        Unsafe.getUnsafe();
    }
}
