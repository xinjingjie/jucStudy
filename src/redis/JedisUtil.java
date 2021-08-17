package redis;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author： xinjingjie
 * @Date：2021/6/23 16:08
 **/
public class JedisUtil {
    public static JedisSentinelPool jedisSentinelPool;

    static {
        createJedisPool();
    }

    public static JedisSentinelPool getJedisSentinelPool() {
        return jedisSentinelPool;
    }

    private static void createJedisPool() {
        // 建立连接池配置参数
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(50);
        // 设置最大阻塞时间，记住是毫秒数milliseconds
        config.setMaxWaitMillis(100);
        // 设置空间连接
        config.setMaxIdle(100);
        // jedis实例是否可用
//        boolean borrow = prop.getProperty("TEST_ON_BORROW") == "false" ? false : true;
        // 创建连接池
//      pool = new JedisPool(config, prop.getProperty("ADDR"), StringUtil.nullToInteger(prop.getProperty("PORT")), StringUtil.nullToInteger(prop.getProperty("TIMEOUT")));// 线程数量限制，IP地址，端口，超时时间
        //获取redis密码
        String masterName = "mymaster";
        Set<String> sentinels = new HashSet<String>();
//        sentinels.add("127.0.0.1:6380");
//        sentinels.add("127.0.0.1:6381");
//        sentinels.add("192.168.6.124:46379");
        sentinels.add("192.168.6.220:16379");
        jedisSentinelPool = new JedisSentinelPool(masterName, sentinels, config);
    }
}
