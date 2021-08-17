package thread.feature.bulk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author： xinjingjie
 * @Date：2021/7/26 13:43
 **/
public class BulkFutureTask {
    static int x = 0;
    static int z = 0;
    static AtomicInteger integer = new AtomicInteger(0);
    ArrayBlockingQueue<Bulk> workBulks = new ArrayBlockingQueue<>(20);
    private List<Integer> bulkIdList = new ArrayList<>();
    private Bulk unFinishBulk;

    public void execute() throws InterruptedException {
        System.out.println("bulkIdList---" + bulkIdList);
        if (bulkIdList.size() == 0) {
            z = z + 10;
            System.out.println("x==" + x);
            System.out.println("z==" + z);
            for (; x < z; x++) {
                workBulks.add(new Bulk(x));
            }
        }
        if (unFinishBulk == null) {
            Bulk b = workBulks.poll();
            if (b != null) {
                bulkIdList.add(b.id);
                createTask(b, bulkIdList, true);
            } else {
                bulkIdList.clear();
            }
        } else {
            createTask(unFinishBulk, bulkIdList, false);
        }

    }

    private void createTask(Bulk bulk, List<Integer> bulkIdList, boolean flag) throws InterruptedException {
//        System.out.println(bulk.id);
//        System.out.println(bulkIdList);
        ExecutorService service = null;
        System.out.println("执行了" + integer.getAndIncrement() + "次");

        if (!bulk.isExecuted) {
            unFinishBulk = bulk;
            service = Executors.newFixedThreadPool(14);
            List<Callable<Object>> list = new ArrayList<>(20);
            AtomicInteger ii = new AtomicInteger(0);
            for (int i = 0; i < 20; i++) {
                list.add(() -> {
                    System.out.println(Thread.currentThread().getName()+"will sleep" + bulk.id + "." + ii.getAndIncrement());
                    TimeUnit.SECONDS.sleep(5);
                    bulk.isExecuted = true;
                    System.out.println("done");
                    System.out.println();
                    System.out.println();
                    return null;
                });
            }
            service.invokeAll(list);
            list.clear();
            unFinishBulk = null;
        } else {
            unFinishBulk = null;
        }
        if (service != null) {
            service.shutdown();
        }

    }

}
