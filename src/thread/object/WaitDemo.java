package thread.object;

import java.util.concurrent.CountDownLatch;

/**
 * @Author： xinjingjie
 * @Date：2021/7/1 13:50
 **/
public class WaitDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        WaitDemo waitDemo = new WaitDemo();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "--start");

                synchronized (waitDemo) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "--wait");
                        countDownLatch.countDown();
                        waitDemo.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "--end");

            }, "thread" + i).start();
        }
        countDownLatch.await();
        synchronized (waitDemo) {
            System.out.println("--notify all--");
//            waitDemo.notify();
//            waitDemo.notify();
            waitDemo.notifyAll();
        }
    }
}
