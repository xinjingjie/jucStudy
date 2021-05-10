package juc.concurrentHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author： xinjingjie
 * @Date：2021/3/1 14:26
 * HashMap 不安全的demo
 **/
public class HashMapUnsafeDemo {
    public static Map<Integer, Integer> mp = new HashMap<>();
    public static Map<Integer, Integer> cmp = new ConcurrentHashMap<>();
    static AtomicInteger integer = new AtomicInteger(0);
    static int num;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> dod()).start();
        }

    }

    public static void dod() {
        for (int j = 0; j < 100000; j++) {
            mp.put(j, j);
            cmp.put(j, j);
            integer.incrementAndGet();
            //线程不安全 15121  100656
            System.out.println("mp:" + mp.size());  // 100279
            System.out.println("cmp:" + cmp.size()); // 100000
            System.out.println("total- atomic:" + integer.get()); //1000000
            System.out.println("total- int :" + ++num); //999988
        }
    }
}
