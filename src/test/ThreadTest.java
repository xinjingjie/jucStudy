package test;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author： xinjingjie
 * @Date：2021/4/22 10:56
 **/
public class ThreadTest {
    @Test
    public void testBlocked() throws Exception {
        class Counter {
            int counter;
            public synchronized void increase() {
                counter++;
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        Counter c = new Counter();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                c.increase();
            }
        }, "t1线程");
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                c.increase();
            }
        }, "t2线程");
        t2.start();

        Thread.sleep(100); // 确保 t2 run已经得到执行
        Assert.assertEquals(t2.getState(),Thread.State.BLOCKED);
    }
}
