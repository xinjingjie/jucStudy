package thread.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author： xinjingjie
 * @Date：2021/7/9 11:05
 **/
public class ExecutorServiceDemo {
    static List<String> raw = new ArrayList<>();

    static {
        for (int i = 0; i < 1000; i++) {
            raw.add(i + "");
        }
    }

    private LinkedBlockingQueue<String> workQue = new LinkedBlockingQueue<>();

    private String unFinishBulk = null;

    public static void main(String[] args) throws InterruptedException {
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println("start");
                    new ExecutorServiceDemo().t();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 1,10000);
    }

    public void t() throws InterruptedException {
        List<String> cList = new ArrayList<>();

        workQue.addAll(raw);

        if (unFinishBulk == null) {
            String poll = workQue.poll();
            if (poll != null) {
                cList.add(poll);
                createTask(poll, cList);
            }
        }
    }

    public void createTask(String s, List<String> cList) throws InterruptedException {
        unFinishBulk = s;
        List<Callable<Object>> list = new ArrayList<>();
        cList.forEach(l -> list.add(new Callable() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(2000);
                System.out.println("hello" + l);
                raw.remove(l);
                return null;
            }
        }));
        ExecutorService executorService = Executors.newFixedThreadPool(14);
        executorService.invokeAll(list);
        executorService.shutdown();
    }

}
