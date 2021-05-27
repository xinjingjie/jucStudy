package juc.forkjoin;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * @Author： xinjingjie
 * @Date：2021/5/27 16:25
 **/
public class ForkJoinDemo {
    public static void main(String[] args) {
        int x = 10000000;
        Instant start = Instant.now();
        long result = ttt(x);
        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(start, end).toMillis() + "ms");
        System.out.println("结果为：" + result);
        System.out.println(Runtime.getRuntime().availableProcessors());

        Instant start2 = Instant.now();
        long result2 = LongStream.rangeClosed(0, 10000000L).parallel().reduce(1, (left, right) -> left + right);
        long result3 = LongStream.rangeClosed(0, 10000000L).parallel().reduce(1, Long::sum);
        Instant end2 = Instant.now();
        System.out.println("耗时：" + Duration.between(start2, end2).toMillis() + "ms");
        System.out.println("结果为：" + result2);
    }

    public static long ttt(int x) {
        long[] numbers = LongStream.rangeClosed(1, x).toArray();
        long total = 0;
        for (long i : numbers) {
            total += i;
        }
        return total;
    }
}
