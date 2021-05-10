package thread.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author： xinjingjie
 * @Date：2021/4/21 14:34
 **/
public class ThreadTest {
    public static int i = 1;
    static CountDownLatch countDownLatch =new CountDownLatch(1);
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                r();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"TimeWaitingStatus");
//        Thread thread2 = new Thread(() -> r());
//        Thread thread3 = new Thread(() -> r());
//        Thread thread4 = new Thread(() -> r());
//        Thread thread5 = new Thread(() -> r());
        System.out.println(thread.getState());

        thread.start();
        System.out.println(thread.getState());


//        thread2.start();
//        thread3.start();
//        thread4.start();
//        thread5.start();


//        System.out.println(X.BLOCKED.getClass());

        countDownLatch.await();

        System.out.println(thread.getState());
//        thread.interrupt();
//        System.out.println(thread.getState());

    }

    public static void r() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getState());

        for (int x = 0; x < 1; x++) {
            i = ++i;
            System.out.println(i);
        }
        countDownLatch.countDown();
    }

/**
 *NEW
 * 当使用 new Thread() 创建一个新的线程，又还没有开始执行（not yet started）它的时候就处于 NEW 状态
 *
 *TERMINATED
 * 终止状态，这个也没什么好说的，完成了执行后（completed execution）或者说退出了（exited）。线程就进入了终止状态。
 *
 * RUNNABLE：前面有提到，它指“正在 Java 虚拟机中执行”。
 *
 * BLOCKED：等待监视器锁（waiting for a monitor lock
 *
 * WAITING：无限期等待另一个线程执行一个特别的动作（waiting indefinitely for another thread to perform a particular action ）。
 *
 * TIMED_WAITING：限时等待另一个线程执行一个动作（waiting for another thread to perform an action for up to a specified waiting time ）。
 */
}
