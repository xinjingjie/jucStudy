package redis.geo;

import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @Author： xinjingjie
 * @Date：2021/8/16 16:31
 **/
public class GeoDemo {
    public static void main(String[] args) {
//        JedisSentinelPool sentinelPool = JedisUtil.getJedisSentinelPool();
        Jedis jedis = new Jedis("112.124.44.175");
//        Jedis jedis = sentinelPool.getResource();
        jedis.geoadd("hangzhou", 120.106231, 30.346064, "hexing");
        jedis.geoadd("hangzhou", 120.118968, 30.345707, "Number6Building");
        jedis.geoadd("hangzhou", 120.101199, 30.347072, "xiTianCity");
        jedis.geoadd("hangzhou", 120.127903, 30.337619, "wanda");
        jedis.geoadd("hangzhou", 114.262143, 30.627061, "hankou");

//        System.out.println(jedis.geodist("hangzhou","hexing","Number6Building", GeoUnit.KM));
//        System.out.println(jedis.geodist("hangzhou","hexing","hankou", GeoUnit.KM));
//        System.out.println(jedis.geodist("hangzhou","hexing","jingdu", GeoUnit.KM));

        List<GeoRadiusResponse> list = jedis.georadius("hangzhou", 120.106231, 30.346064, 2, GeoUnit.KM);
        for (GeoRadiusResponse r : list) {
            System.out.println(r.getMemberByString());
        }
    }
}
