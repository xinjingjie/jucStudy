package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author： xinjingjie
 * @Date：2021/6/11 16:09
 **/
public class JedisConcurrentProblemDemo {
    static AtomicInteger integer = new AtomicInteger(0);

    static JedisSentinelPool jedisSentinelPool;

    public static void main(String[] args) throws InterruptedException {
        jedisSentinelPool = JedisUtil.getJedisSentinelPool();
        test();
    }

    public static void test() throws InterruptedException {

        int n = 5;
        CountDownLatch c = new CountDownLatch(n);
        jedisSentinelPool.getResource().set("count", "0");

        Instant begin = Instant.now();
        for (int x = 0; x < n; x++) {
            new Thread(() -> {
                Jedis jedis = jedisSentinelPool.getResource();

                for (int i = 0; i < 2000; i++) {
                    //解决方案
//                    Long count = jedis.incr("count");
                    jedis.set( "count", String.valueOf(Integer.parseInt(jedis.get("count"))+1));
//                    System.out.println(Thread.currentThread().getName() + "--" + count);
                    integer.incrementAndGet();
                }
                c.countDown();
            }).start();
        }
        c.await();

        Instant end = Instant.now();
        System.out.println("cost time:" + Duration.between(begin, end).toMillis());
        System.out.println(jedisSentinelPool.getResource().get("count"));
        System.out.println("integer:==" + integer);
        jedisSentinelPool.close();
    }




}
