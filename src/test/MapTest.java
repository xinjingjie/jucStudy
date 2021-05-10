package test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author： xinjingjie
 * @Date：2021/3/22 14:25
 **/
public class MapTest {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 1);
        map.put("3", 1);
        map.put("4", 1);
        map.compute("4", (k, v) -> v = 10);
        System.out.println(map.get("1"));
        System.out.println(map.get("4"));

//        throw new InterruptedException();

//        try {
//            Thread.currentThread().interrupt();
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//
//        try {
//            TimeUnit.SECONDS.sleep(1);
//            Thread.currentThread().interrupt();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        final Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (map) {
                        wait(10000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
//        Thread t2 = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                synchronized (map) {
//                    t1.interrupt();
//                }
//            }
//        };
        t1.start();
//        t2.start();

    }
}
