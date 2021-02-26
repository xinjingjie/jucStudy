package juc.lock;

import juc.BlockingQueueDemo;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author： xinjingjie
 * @Date：2021/2/24 16:12
 **/
public class SimpleTest {
    static BlockingQueueDemo  bqd = new BlockingQueueDemo(10);

    public static void main(String[] args) throws InterruptedException {
        new Thread(()-> {
            try {
                p();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        for (int i = 0; i < 12; i++) {
            System.out.println(bqd.offer(1, 10, TimeUnit.SECONDS));
            System.out.println();
        }
//        p();
    }

    public static void p() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        System.out.println("take:"+bqd.take());
        System.out.println();
    }



}
