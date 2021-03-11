package juc.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author： xinjingjie
 * @Date：2021/3/3 14:59
 **/
public class CyclicBarrierDemo {
    public static void main(String[] args) throws InterruptedException {
        int threadNum = 5;
        CyclicBarrier barrier = new CyclicBarrier(threadNum, new Runnable() {
            @Override
            public void run() {
                System.out.println("这是只有最后的一个任务才会执行");
                System.out.println(Thread.currentThread().getName() + " 完成最后任务");
            }
        });

        for (int i = 0; i < threadNum; i++) {
            new TaskThread(barrier).start();
        }
    }

    static class TaskThread extends Thread {
        final CyclicBarrier barrier;

        public TaskThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                synchronized (barrier) {
                    System.out.println(getName() + " 第" + (barrier.getNumberWaiting() + 1) + "个到达栅栏 A");
                }
                barrier.await();
                System.out.println(getName() + " 冲破栅栏 A");
                Thread.sleep(2000);
                System.out.println(getName() + " 到达栅栏 B");
                barrier.await();
                System.out.println(getName() + " 冲破栅栏 B");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
