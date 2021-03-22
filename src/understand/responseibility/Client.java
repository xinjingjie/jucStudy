package understand.responseibility;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author： xinjingjie
 * @Date：2021/3/22 9:40
 **/
public class Client {
    public static void main(String[] args) {
        ThirdRequest thirdRequest = new ThirdRequest();
        thirdRequest.start();
        SecondRequest secondRequest = new SecondRequest(thirdRequest);
        FirstRequest firstRequest = new FirstRequest(secondRequest);
        firstRequest.start();
        List<MyOrder> myOrders = new ArrayList<>();
        MyOrder order = new MyOrder("Orange", 1111111);
        MyOrder order1 = new MyOrder("apple", 222222);
        MyOrder order2 = new MyOrder("banana", 333333);
        myOrders.add(order);
        myOrders.add(order1);
        myOrders.add(order2);
        for (int i = 0; i <= 20; i++) {
            MyOrder myOrder = new MyOrder("goods", i);
            myOrders.add(myOrder);
        }
        for (MyOrder o : myOrders) {
            firstRequest.process(o);
        }
    }
}
