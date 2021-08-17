package redis;

import org.openjdk.jol.util.ObjectUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author： xinjingjie
 * @Date：2021/6/23 11:13
 **/
public class RedisTest {

    static JedisSentinelPool jedisSentinelPool;

    static boolean flag = true;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        jedisSentinelPool = JedisUtil.jedisSentinelPool;
//        hSetTest();
//
//        listTest();
        get();
    }

    public static void get() throws IOException, ClassNotFoundException {

//        Jedis jedis = jedisSentinelPool.getResource();
        Jedis jedis = new Jedis("192.168.6.124");
        String select = jedis.select(0);
        System.out.println(select);
        List<byte[]> list=jedis.lrange(getByte("D:BIZ:BIZ_PUB_DATA_OTHER_M:ALL:DATA:LO"),0,1);
        List<Object> value = new ArrayList<>();
        for (byte[] bs:list){
            value.add(toObject(bs));
        }
        jedis.close();

        List<Map<String,Object>> dataLs=new ArrayList<>();
        if (value!=null){
            for (Object o:value){
                dataLs.add((Map<String, Object>) o);
            }
        }
        for (Map<String, Object> data:dataLs) {
            System.out.println(data.entrySet());
        }
    }

    public static Object toObject(byte[] bs) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis=new ByteArrayInputStream(bs);
        ObjectInputStream ois =new ObjectInputStream(bis);
        Object obj = ois.readObject();
        ois.close();
        bis.close();

        return obj;
    }
    public static byte[] getByte(Object o){
        return String.valueOf(o).getBytes();
    }
    public static void hSetTest() {
        Jedis jedis = jedisSentinelPool.getResource();
        jedis.hset("ghz:info", "name", "ghz");
        jedis.hset("ghz:info", "age", "16");
        jedis.hset("ghz:info", "sex", "male");
    }

    public static void listTest() {
        Jedis jedis = jedisSentinelPool.getResource();
        lpush(jedis, 0L);
        rpush(jedis,25L);
    }


    public static Long lpush(Jedis jedis, Long num) {
        if (num > 24) {
            return 24L;
        } else {
            num++;
            return lpush(jedis, jedis.lpush("JIntList", String.valueOf(num)));
        }
    }

    public static Long rpush(Jedis jedis, Long num) {
        if (num > 50) {
            return 50L;
        } else {
            num++;
            return rpush(jedis, jedis.rpush("JIntList", String.valueOf(26-num)));
        }
    }
}
