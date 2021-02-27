package juc.BlockingQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Author： xinjingjie
 * @Date：2021/2/26 16:18
 **/
public class Test {
    public static void main(String[] args) throws InterruptedException {
        Object[] o=new Object[1];;
        System.out.println(o[0]);


        BlockingQueueDemo<Integer> b=new BlockingQueueDemo<>(2);
        System.out.println(b.offer(1));  //true    1
        System.out.println(b.offer(2)); //true     1  2
        System.out.println(b.offer(3)); //false    1  2
        System.out.println(b.peek());       //1       1  2
        System.out.println(b.take());       //1       2
//        System.out.println(b.toArray());
        b.put(3);                        //        2  3
        List list=new ArrayList();
        System.out.println(b.drainTo(list));
        System.out.println(list);

        System.out.println(b.peek());      //2        2  3
        System.out.println(b.take());      //         3

        System.out.println(b.take());      //         null


        System.out.println(b.remove());

    }
}
