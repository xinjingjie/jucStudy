package test;

import redis.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.io.IOException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author： xinjingjie
 * @Date：2021/7/23 13:52
 **/
public class BytesTest {
    public static void main(String[] args) throws IOException {
        Map<String, BitSet> profileAndBitSet = new HashMap<>();
        BitSet b = new BitSet(200);
        b.set(Integer.parseInt("200"));
        profileAndBitSet.put("700067", b);

        Jedis resource = JedisUtil.getJedisSentinelPool().getResource();
        for(int i=0;i< 256;i++){
            System.out.println(resource.getbit("D:10000:12:00:00",i));
        }
        Pipeline pipelined = resource.pipelined();
        Response<String> response = pipelined.get("D:10000:12:00:00");
        pipelined.close();
        String bitmap = response.get();


        System.out.println(1 & 2);
        System.out.println("---");
        System.out.println(bitmap);
        System.out.println("---");


        byte[] bytes = bitmap.getBytes();
        for (byte bb : bytes) {
            System.out.print((int) bb + ",");
        }
        System.out.println();

        System.out.println(1 << 5);
        System.out.println("bytes.length  " + bytes.length);

        int size = bytes.length * 8;
        System.out.println("size  " + size);

        BitSet bitSet = getBitSet(bytes);
        for (int i = 0; i < bitSet.size(); i++) {
            System.out.println(i + ":" + bitSet.get(i));
        }


        System.out.println("bitSet.get(200)" + bitSet.get(200));
        System.out.println("bitSet.get(201)" + bitSet.get(201));
        System.out.println("bitSet.get(225)" + bitSet.get(225));
//        for (int i = 0; i < size; i++) {
//            if (i == 200) {
//                System.out.println(profileAndBitSet.get("700067").get(i));
//
//                System.out.println(i / 8);
//                System.out.println(i % 8);
//
//                System.out.println("bytes[i / 8]  " + bytes[i / 8]);
//                System.out.println("1 << (7 - (i % 8))  " + (1 << (7 - (i % 8))));
//                System.out.println(bytes[i / 8] & (1 << (7 - (i % 8))));
//                System.out.println("-----");
//            }
//            if (profileAndBitSet.get("700067").get(i) && (bytes[i / 8] & (1 << (7 - (i % 8)))) == 0) {
//                System.out.println("-d-s-a-d-sa");
//            }
//        }
    }

    public static BitSet getBitSet(byte[] bytes) {
        BitSet bits = new BitSet();
        for (int i = 0; i < bytes.length * 8; i++) {
            if ((bytes[i / 8] & (1 << (7 - (i % 8)))) != 0) {
                bits.set(i);
            }
        }
        return bits;
    }
}
