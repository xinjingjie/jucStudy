package test;

import java.util.concurrent.TimeUnit;

/**
 * @Author： xinjingjie
 * @Date：2021/3/10 15:56
 **/
public class MyUtil {
    static void ddd(int i) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("------"+i+"--------");
    }
}
