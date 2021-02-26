package juc;

import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @Author： xinjingjie
 * @Date：2021/2/24 10:11
 **/
public class BlockingQueueDemo<E> extends AbstractQueue<E> implements BlockingQueue<E>, Serializable {

    private static final long serialVersionUID = 1L;
    final Object[] items;

    int takeIndex;
    int putIndex;
    int count;
    final ReentrantLock lock;
    private final Condition notEmpty;
    private final Condition notFull;

    public BlockingQueueDemo(int capacity) {
        this(capacity, false);
    }

    public BlockingQueueDemo(int capacity, boolean fair) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.items = new Object[capacity];
        this.lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }


    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void put(E e) throws InterruptedException {

    }

    @Override
    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        checkNotNull(e);
        long nanos = unit.toNanos(timeout);
        lock.lockInterruptibly();
        try {
            while (count == items.length) {
                if (nanos <= 0) {
                    return false;
                }
                System.out.println("!!!!!!!!!!!hello!!!!!!!!!!!!!!");
                //造成当前线程在接到信号、被中断或到达指定等待时间之前一直处于等待状态。
                // 返回值表示剩余时间，如果在nanosTimesout之前唤醒，那么返回值 = nanosTimeout - 消耗时间，如果返回值 <= 0 ,则可以认定它已经超时了。
                nanos = notFull.awaitNanos(nanos);
                System.out.println(nanos);
            }
            enqueue(e);
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E take() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == 0)
                notEmpty.await();
            return dequeue();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public int remainingCapacity() {
        return 0;
    }

    @Override
    public int drainTo(Collection<? super E> c) {
        return 0;
    }

    @Override
    public int drainTo(Collection<? super E> c, int maxElements) {
        return 0;
    }

    @Override
    public boolean offer(E e) {
        //判空
        checkNotNull(e);
        //重新赋值锁
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            if (count == items.length) {
                return false;
            } else {
                enqueue(e);
                return true;
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E poll() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return (count == 0) ? null : dequeue();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E peek() {
        return null;
    }


    @Override
    public boolean add(E e) {
        return super.add(e);
    }

    @Override
    public E remove() {
        return super.remove();
    }

    @Override
    public E element() {
        return super.element();
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return super.addAll(c);
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public Object[] toArray() {
        return super.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return super.toArray(a);
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return super.containsAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return super.retainAll(c);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return false;
    }

    @Override
    public Spliterator<E> spliterator() {
        return null;
    }

    @Override
    public Stream<E> stream() {
        return null;
    }

    @Override
    public Stream<E> parallelStream() {
        return null;
    }

    private static void checkNotNull(Object v) {
        if (v == null)
            throw new NullPointerException();
    }

    private void enqueue(E x) {
        // assert lock.getHoldCount() == 1;
        // assert items[putIndex] == null;
        final Object[] items = this.items;
        //赋值
        items[putIndex] = x;
        //如果是最后一个，putIndex置0
        if (++putIndex == items.length)
            putIndex = 0;
        count++;
        notEmpty.signal();
    }

    private E dequeue() {
        // assert lock.getHoldCount() == 1;
        // assert items[takeIndex] != null;
        final Object[] items = this.items;
        @SuppressWarnings("unchecked")
        E x = (E) items[takeIndex];
        items[takeIndex] = null;
        if (++takeIndex == items.length)
            takeIndex = 0;
        count--;
//        if (itrs != null)
//            itrs.elementDequeued();
        notFull.signal();
        return x;
    }
}
