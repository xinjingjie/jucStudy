package thread.completableFuture;

import java.util.concurrent.*;

/**
 * @Author： xinjingjie
 * @Date：2021/5/31 10:08
 **/
public class CompletableFutureDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("executorService 是否为守护线程 :" + Thread.currentThread().isDaemon());
                return null;
            }
        });


        final CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync 是否为守护线程 " + Thread.currentThread().isDaemon());
            //                TimeUnit.SECONDS.sleep(2);
            System.out.println("this lambda is executed by forkJoinPool");

            //因为是守护线程，所以其他非守护线程一结束，就立即结束
            int i = 0;
            while (i < 10) {
                System.out.println("i will executed");
                try {
                    TimeUnit.SECONDS.sleep(1);
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "result1";
        });

//        executorService

        final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync 使用executorService 时是否为守护线程 : " + Thread.currentThread().isDaemon());
            return "result2";
        }, executorService);


        System.out.println(completableFuture.get());
        System.out.println(future.get());

        executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        completableFuture.complete("hello");

        CompletableFuture.allOf(completableFuture, future);

        System.out.println("nmd");
        executorService.shutdown();
    }
}
