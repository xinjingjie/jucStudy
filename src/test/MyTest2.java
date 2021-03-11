package test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author： xinjingjie
 * @Date：2021/3/5 19:32
 **/
public class MyTest2 {
    static AtomicInteger i = new AtomicInteger(0);

    public static void main(String[] args) {
        Runnable a = new MyRunnable(i);
        new Thread(a).start();
        new Thread(a).start();
        new Thread(a).start();

    }

}

class MyRunnable implements Runnable {
    AtomicInteger i;

    MyRunnable(AtomicInteger i) {
        this.i = i;
    }

    @Override
    public void run() {

            for (int j = 0; j < 3; j++) {
                while (i.get() <= 36) {
                    System.out.println(Thread.currentThread().getName() + "--" + i.incrementAndGet());
                }
            }
        }
    }
