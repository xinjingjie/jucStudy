package thread.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author： xinjingjie
 * @Date：2021/5/31 9:55
 **/
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture cf =new CompletableFuture();
        Thread thread= new Thread(()->{
            int res = 1+1;
            System.out.println("future 执行1+1");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cf.complete("任务返回结果"+res);
        });
        thread.start();
        System.out.println(cf.get());
        System.out.println("will i be print early");
     }
}
