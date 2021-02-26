package juc.lock.reentrant;

/**
 * @Author： xinjingjie
 * @Date：2021/2/24 14:21
 * 可重入锁
 * 每个锁关联一个线程持有者和计数器，当计数器为0时表示该锁没有被任何线程持有，
 *      那么任何线程都可能获得该锁而调用相应的方法；
 * 当某一线程请求成功后，JVM会记下锁的持有线程，并且将计数器置为1；
 * 此时其它线程请求该锁，则必须等待；
 * 而该持有锁的线程如果再次请求这个锁，就可以再次拿到这个锁，同时计数器会递增；
 * 当线程退出同步代码块时，计数器会递减，如果计数器为0，则释放该锁。
 **/
public class MyReentrantLock {
    private boolean isLocked = false;
    private Thread lockedBy = null;
    private int lockedCount = 0;

    public synchronized void lock() throws InterruptedException {
        Thread thread = Thread.currentThread();
        if (isLocked && lockedBy != thread) {
            wait();
        }
        isLocked = true;
        lockedCount++;
        lockedBy = thread;
    }

    public synchronized void unlock() {
        Thread thread = Thread.currentThread();
        if (thread == this.lockedBy) {
            lockedCount--;
            if (lockedCount == 0) {
                isLocked = false;
                notify();
            }
        }
    }
}
