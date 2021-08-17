package redis;

import redis.clients.jedis.Jedis;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author： xinjingjie
 * @Date：2021/8/5 16:10
 **/
public class TTTT {
    public static void main(String[] args) {
        Jedis jedis = JedisUtil.getJedisSentinelPool().getResource();
        String select = jedis.select(125);
        Map<String, Object> value = new HashMap<>();
        for (int i = 0; i < 300; i++) {
            value.put("123456"+i, "00");
        }
        value.put("123456342","" );

//        jedis.hmset("R:RATE:ALL:INFO",objectMap);

        Map<byte[], byte[]> map = new HashMap<>();
        for (Map.Entry<String, Object> e : value.entrySet()) {
            map.put(e.getKey().getBytes(), serialize(e.getValue()));
        }
        System.out.println(jedis.hmset("R:RATE:ALL:INFO2".getBytes(), map));
        jedis.close();
    }

    public static byte[] serialize(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
