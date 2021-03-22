package understand.responseibility;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author： xinjingjie
 * @Date：2021/3/22 9:28
 **/
public class FirstRequest extends Thread implements DealOrder {

    private LinkedBlockingQueue<MyOrder> blockingQueue = new LinkedBlockingQueue<>(10);
    DealOrder nextOrder;

    FirstRequest(DealOrder dealOrder) {
        this.nextOrder = dealOrder;
    }

    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while (true) {
            try {
                MyOrder order = blockingQueue.take();
                System.out.println("is creating order--" + order.orderNo + order.orderName);
                TimeUnit.SECONDS.sleep(1);
                System.out.println("order done");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void process(MyOrder order) {
        System.out.println("---- order ----");
        try {
            blockingQueue.put(order);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        nextOrder.process(order);
    }
}
