package thread.feature;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author： xinjingjie
 * @Date：2021/3/11 10:05
 * 子线程去执行计算任务，然后主线程获取每个的结果进行计算
 **/
public class FutureThreadDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        List<FutureTask<Integer>> futureTaskList = new ArrayList<FutureTask<Integer>>();
        for(int i = 0;i < 10 ;i ++){
            final int index = i;
            FutureTask<Integer> ft = new FutureTask<Integer>(new Callable<Integer>() {
                private  Integer result = 0;
                @Override
                public Integer call() throws Exception {
                    for(int j = 0;j <= 100;j++){
                        result += j;
                    }
                    Thread.sleep(5000);
                    System.out.println("子线程：" + index + ",执行完成！"+result);
                    return result;
                }
            });
            futureTaskList.add(ft);
            executorService.submit(ft);
        }

        System.out.println("子线程提交完毕，主线程继续执行！");

        try {
            Thread.sleep(1000 * 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程执行完毕！");

        System.out.println("futureTaskList共有"+futureTaskList.size()+"个任务");
        Integer totalResult = 0;
        for(FutureTask<Integer> ft : futureTaskList){
            try {
                totalResult += ft.get();
                System.out.println(totalResult);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("子线程计算的结果为：" + totalResult);
        executorService.shutdown();

    }
}
