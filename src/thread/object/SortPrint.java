package thread.object;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author： xinjingjie
 * @Date：2021/7/1 14:03
 * @DESCRIBE 两个线程依次打印1-10   利用wait notify
 **/
public class SortPrint {
    public static void main(String[] args) {

        Object o = new Object();
        AtomicInteger i = new AtomicInteger();
        for (int j=0;j<3;j++) {
            new Thread(() -> {
                synchronized (o) {
                    while (i.get() < 50) {
                        try {
                            o.notifyAll();
                            System.out.println(Thread.currentThread().getName() + "-----" + i.get());
                            i.getAndIncrement();
                            if (i.get() != 50) {
                                o.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, "thread" + j).start();
        }
    }
}
