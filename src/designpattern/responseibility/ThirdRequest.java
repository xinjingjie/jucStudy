package designpattern.responseibility;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author： xinjingjie
 * @Date：2021/3/22 9:39
 **/
public class ThirdRequest extends Thread implements DealOrder {
    private LinkedBlockingQueue<MyOrder> blockingQueue = new LinkedBlockingQueue<>(10);

    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while (true) {
            try {
                MyOrder order = blockingQueue.take();
                System.out.println("order is Distributing--" + order.orderNo + order.orderName);
                TimeUnit.SECONDS.sleep(2);
                System.out.println("order deliver done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void process(MyOrder order) {
        try {
            blockingQueue.put(order);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---- process delivering---");

    }
}
