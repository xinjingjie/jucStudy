package thread.feature.bulk;

import thread.threadpool.ExecutorServiceDemo;

import java.time.Duration;
import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author： xinjingjie
 * @Date：2021/7/26 14:47
 **/
public class DoBulkFuture {
    static BulkFutureTask bulkFutureTask;
    static Instant start = Instant.now();

    public static void main(String[] args) {
        bulkFutureTask = new BulkFutureTask();
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                     Instant end = Instant.now();
                    System.out.println(Thread.currentThread().getName()+"耗时：" + Duration.between(start, end).toMillis() + "ms");
                    start=end;
                    System.out.println("定时任务开始执行");
                    bulkFutureTask.execute();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 1,1000);
    }
}
