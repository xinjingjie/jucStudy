package juc.countDownLatch;

import java.util.concurrent.*;

/**
 * @Author： xinjingjie
 * @Date：2021/3/3 14:10
 * 执行完所有的业务后才执行主线程
 **/
public class CountDownLatchDemo3 {
    public static void main(String[] ar) throws InterruptedException {
        CountDownLatch doneSignal = new CountDownLatch(10);
        ThreadPoolExecutor e = new ThreadPoolExecutor(10,
                13,
                1,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));
        for (int i = 0; i < 10; ++i)
            //create and start threads
            e.execute(new WorkerRunnable(doneSignal, i));
        doneSignal.await(); // wait for all to finish
        System.out.println("all is done");
        //关闭线程池
        e.shutdown();
    }
}

class WorkerRunnable implements Runnable {
    private final CountDownLatch doneSignal;
    private final int i;

    WorkerRunnable(CountDownLatch doneSignal, int i) {
        this.doneSignal = doneSignal;
        this.i = i;
    }

    @Override
    public void run() {
        doWork(i);
        doneSignal.countDown();
    }

    void doWork(int i) {
        System.out.println("执行第" + i + "个");
    }
}


