package designpattern.responseibility;

/**
 * @Author： xinjingjie
 * @Date：2021/3/22 9:37
 **/
public class SecondRequest implements DealOrder{
    public DealOrder nextOrder;

    public SecondRequest(DealOrder nextOrder) {
        this.nextOrder = nextOrder;
    }

    @Override
    public void process(MyOrder order) {
        System.out.println("---- process stocking---");
        nextOrder.process(order);
    }
}
