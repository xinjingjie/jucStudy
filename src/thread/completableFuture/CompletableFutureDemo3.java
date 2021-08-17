package thread.completableFuture;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author： xinjingjie
 * @Date：2021/5/31 13:30
 **/
public class CompletableFutureDemo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //对结果进行加工
        String result = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行supplyAsync");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "result";
        }).thenApply(s -> {
            System.out.println("执行thenApply");
            return "处理过的" + s;
        }).join();

        System.out.println(result);
        System.out.println("---");
        System.out.println("---");
        System.out.println("---");
        System.out.println("---");
        System.out.println("---");
        System.out.println("hello world");

        //对结果进行消耗
        CompletableFuture.supplyAsync(() -> {
            return "result";
        }).thenAcceptAsync(s -> System.out.println(s + "被消耗,返回Void"));

        //当得到上一步的结果时的操作
        CompletableFuture.supplyAsync(() -> "result").thenRunAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "is running");
            System.out.println("cant get result");
        });

        //结合两个CompletionStage的结果，进行转化后返回
        Instant i1 = Instant.now();
        String result2 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("正在执行第一个");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "result1";
        }).thenCombine(
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("正在执行第二个");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "result2";
                }), (s1, s2) -> s1 + "和" + s2).join();
        Instant i2 = Instant.now();
        System.out.println("执行时间" + Duration.between(i1, i2).toMillis() + "ms");
        System.out.println(result2);


        //结合两个CompletionStage的结果，进行消耗
        CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("正在执行第一个");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "result1";
        }).thenAcceptBothAsync(CompletableFuture.supplyAsync(() -> "result2"), (s1, s2) -> System.out.println(s1 + s2));
        System.out.println("?");

    }
}
