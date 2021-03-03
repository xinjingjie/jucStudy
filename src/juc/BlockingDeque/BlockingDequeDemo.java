package juc.BlockingDeque;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Author： xinjingjie
 * @Date：2021/3/1 14:11
 **/
//双端阻塞队列,两端都可以进队列，也可以出队列
public class BlockingDequeDemo {
    public static void main(String[] args) {
        //    LinkedBlockingDeque是基于链表的、线程安全的双端阻塞队列
        BlockingDeque<Integer> bd = new LinkedBlockingDeque<>();
        bd.offerFirst(1);
        bd.offerFirst(2);
        bd.offerLast(3);
        bd.offerLast(4);
        // 2 1 3 4
        List<Integer> l=new ArrayList<>();
        System.out.println(bd.drainTo(l));
        l.forEach(System.out::println);
    }
}
