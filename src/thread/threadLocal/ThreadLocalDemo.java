package thread.threadLocal;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author： xinjingjie
 * @Date：2021/3/24 13:33
 * Java中的ThreadLocal类允许我们创建只能被同一个线程读写的变量。
 * 因此，如果一段代码含有一个ThreadLocal变量的引用，即使两个线程同时执行这段代码，它们也无法访问到对方的ThreadLocal变量。
 **/
public class ThreadLocalDemo {

    public static void main(String[] args) {
        ThreadId threadId = new ThreadId();
        Thread thread1 = new Thread(threadId);
        Thread thread2 = new Thread(threadId);
        Thread thread3 = new Thread(threadId);
        Thread thread4 = new Thread(threadId);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    public static class ThreadId implements Runnable {
        // Atomic integer containing the next thread ID to be assigned
        private  static final AtomicInteger NEXT_ID = new AtomicInteger(0);
        private  static final int x =1;
        // Thread local variable containing each thread's ID
        private  final ThreadLocal<Integer> THREAD_ID =
                ThreadLocal.withInitial(NEXT_ID::getAndIncrement);


        // Returns the current thread's unique ID, assigning it if necessary
        public  int get() {
            return THREAD_ID.get();
        }

        @Override
        public void run() {
            System.out.println(THREAD_ID.get());
            System.out.println("x="+x);
        }
    }

}

