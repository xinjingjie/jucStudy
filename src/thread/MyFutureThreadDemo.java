package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author： xinjingjie
 * @Date：2021/3/15 11:05
 **/
public class MyFutureThreadDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(8, 10, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new MyThreadFactory());
        List<FutureTask<Integer>> futureTasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            FutureTask<Integer> ft = new FutureTask<>(new Callable<Integer>() {
                int count = 0;
                @Override
                public Integer call() throws Exception {
                    int ran = new Random().nextInt(1000);
                    for (int j = 0; j < ran; j++) {
                        count += j;
                    }
                    System.out.println(count);
                    return count;
                }
            });
            futureTasks.add(ft);
            executorService.submit(ft);
        }

        int all = 0;
        for (FutureTask<Integer> futureTask : futureTasks) {
            all += futureTask.get();
            System.out.println(futureTask.cancel(true));
        }
        System.out.println("count result:"+all);
        executorService.shutdown();
    }

    static class MyThreadFactory implements ThreadFactory {
        private AtomicInteger atomicInteger = new AtomicInteger(0);
        //线程组
        private final ThreadGroup group;

        MyThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s == null) ? new ThreadGroup("DefaultThreadGroup") : s.getThreadGroup();
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, "thread-" + atomicInteger.getAndIncrement());
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            return thread;
        }
    }
}
