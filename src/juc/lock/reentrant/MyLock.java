package juc.lock.reentrant;

/**
 * @Author： xinjingjie
 * @Date：2021/2/24 14:21
 * 不可重入锁
 **/
public class MyLock {
    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException {
        while (isLocked) {
            wait();
        }
        isLocked = true;
    }

    public synchronized void unlock() {
        isLocked = false;
        notify();
    }


}
