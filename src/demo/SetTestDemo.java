package demo;

import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * 定时任务
 * @Author： xinjingjie
 * @Date：2021/5/12 15:30
 **/
public class SetTestDemo {
    private static ScheduledExecutorService mScheduledExecutorService = Executors.newScheduledThreadPool(4);

    public static void main(String[] args) {
        long n = System.currentTimeMillis();
        Date date = new Date();
        date.setTime(n + 5 * 1000);
        ddd(date.getTime() - n);
        date.setTime(n + 1000);
        ddd(date.getTime() - n);
        date.setTime(n + 1000);
        ddd(date.getTime() - n);
        date.setTime(n + 1000);
        ddd(date.getTime() - n);
    }

    static void ddd(long interval) {
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                System.out.println("HELLO EVERYONE");
            }
        };

        mScheduledExecutorService.schedule(tt, interval, TimeUnit.MILLISECONDS);
    }
}
