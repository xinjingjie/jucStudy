package thread.object;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author： xinjingjie
 * @Date：2021/7/1 16:11
 **/
public class ThreeSortPrint2 {
    static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        AtomicInteger num = new AtomicInteger();

        final int[] num2 = {0};

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                while (num.get() < 20) {
                    try {
                        reentrantLock.lock();
                        //双重判断，防止多执行
                        if (num.get() >=20){
                            return;
                        }
                        while (num.get() % 5 == finalI) {
                            System.out.println(Thread.currentThread().getName() + "-----" + num.getAndIncrement());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        reentrantLock.unlock();
                    }
                }
            }, "thread"+i).start();
        }

    }
}
