package juc.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Author： xinjingjie
 * @Date：2021/3/3 13:43
 *  到达某一时刻一起执行
 **/
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    //准备完毕……运动员都阻塞在这，等待号令
                    countDownLatch.await();
                    String parter = "【" + Thread.currentThread().getName() + "】";
                    System.out.println(parter + "开始执行……");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        // 裁判准备发令
        Thread.sleep(2000);
        // 发令枪：执行发令
        countDownLatch.countDown();
    }
}



