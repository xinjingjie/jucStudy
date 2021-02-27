package juc.BlockingQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author： xinjingjie
 * @Date：2021/2/27 13:23
 **/
public class ModeTest {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new ArrayBlockingQueue(20);
        MyProducer m1 = new MyProducer(queue);
//        MyProducer m2 = new MyProducer(queue);

        Thread thread1 = new Thread(m1, "thread1");
//        Thread thread2 = new Thread(m2, "thread2");
        thread1.start();
//        thread2.start();

        TimeUnit.SECONDS.sleep(2);

        for (int i = 10; i < 50; i += 10) {
            System.out.println("Thread1放入" + i);
            m1.put(i);
        }
//        for (int i = 10; i < 50; i += 10) {
//            System.out.println("Thread2放入" + i);
//            m2.put(i);
//        }
//
//        System.out.println();

        System.out.println("thread1关闭");
        m1.close();

        for (int i = 50; i < 100; i += 10) {
            System.out.println("Thread1放入" + i);
            m1.put(i);
        }
//        System.out.println("thread2关闭");
//        m2.close();

//        thread1.join();
        System.out.println(queue.size());


    }

    static class MyProducer implements Runnable {

        private final BlockingQueue queue;
        private  List<Object> putData;
        private  boolean flag = false;
        private  volatile boolean closeFlag = false;
        private   AtomicInteger getIndex;
        private  AtomicInteger allIndex;
//        ReentrantLock lock=new ReentrantLock();

        MyProducer(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {

            try {
                System.out.println("生产者线程启动");
                while (!closeFlag) {
//                    lock.lock();
                    if (allIndex != null && getIndex != null && putData != null) {
                        if (allIndex.get() > getIndex.get()) {
                            System.out.println(Thread.currentThread().getName() + "正在放数据:" + putData.get(getIndex.get()));
                            TimeUnit.SECONDS.sleep(1);
                            queue.put(putData.get(getIndex.get()));
                            System.out.println("数据放进去了");
                            getIndex.incrementAndGet();
                            if (allIndex.get() == getIndex.get()) {
                                putData = null;
                                if (flag) {
                                    System.out.println(Thread.currentThread().getName() + "马上关闭");
                                    break;
                                }
                            }
                        }
                    }
                    else if (flag) {
                        System.out.println(">>>>");
                        closeFlag = true;
                        System.out.println(Thread.currentThread().getName() + "马上关闭");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
//            }finally {
//                lock.unlock();
            }
        }

        public  void put(Object o) {
            if (putData == null) {
                putData = Collections.synchronizedList(new ArrayList<Object>());
                getIndex = new AtomicInteger(0);
                allIndex = new AtomicInteger(0);
            }
            putData.add(o);
            allIndex.incrementAndGet();
        }

        public void close() {
            flag = true;
        }
    }

//    class MyConsumer implements Runnable {
//
//        final BlockingQueue queue;
//
//        MyConsumer(BlockingQueue queue) {
//            this.queue = queue;
//        }
//
//        @Override
//        public void run() {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//                System.out.println("正在放数据");
//                queue.put(putData);
//                System.out.println("数据放进去了");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        public void get(Object o) {
//            queue=o;
//        }
//    }
}
