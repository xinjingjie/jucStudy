package juc.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Author： xinjingjie
 * @Date：2021/3/3 14:26
 * 执行完所有的业务后才执行主线程
 **/
public class CountDownLatchDemo4 {
    final static CountDownLatch cdl=new CountDownLatch(10);
    public static void main(String[] args) throws InterruptedException {
        long begin=System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> t(),"thread-"+i).start();
        }
        cdl.await();
        System.out.println("other thread is done,main thread start");
        long end=System.currentTimeMillis();
        System.out.println("耗时"+(end-begin)+"ms");
    }

    public static void t() {
        System.out.println(Thread.currentThread().getName() + " is doing");
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " is over");
            cdl.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
