package thread.object;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author： xinjingjie
 * @Date：2021/7/1 14:38
 * 多个线程依次输出
 **/
public class ThreeSortPrint1 {

    static AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        MThread t1 = new MThread(a, b);
        MThread t2 = new MThread(b, c);
        MThread t3 = new MThread(c, a);

        new Thread(t1, "thread1").start();
        Thread.sleep(100);
        new Thread(t2, "thread2").start();
        Thread.sleep(100);
        new Thread(t3, "thread3").start();
        //必须按顺序执行

    }

    static class MThread implements Runnable {

        private final Object prev;
        private final Object self;

        public MThread(Object prev, Object self) {
            this.prev = prev;
            this.self = self;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "start");
            while (num.get() < 50) {
                synchronized (prev) {
                    synchronized (self) {
                        self.notifyAll();
                        System.out.println(Thread.currentThread().getName() + "-----" + num.getAndIncrement());
                    }
                    try {
                        if (num.get() > 50) {
                            prev.wait();
                        }else{
                            prev.notify();
                            prev.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
