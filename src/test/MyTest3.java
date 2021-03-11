package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author： xinjingjie
 * @Date：2021/3/10 15:55
 **/
public class MyTest3 {
    public static void main(String[] args) {
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(20, 22, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(80));
//        for (int i=0; i<20;i++) {
//            executor.execute(new MyR(i));
//        }
//        executor.shutdown();
        for (int i=0; i<20;i++) {
            MyR myR=new MyR(i);
            new Thread(myR).start();
        }

        System.out.println("hello");

    }

    static class MyR implements Runnable {
        int i;
        MyR(int i){
            this.i=i;
        }
        @Override
        public void run() {
            try {
                MyUtil.ddd(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
