package juc.concurrentHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.sun.xml.internal.fastinfoset.util.ValueArray.MAXIMUM_CAPACITY;

/**
 * @Author： xinjingjie
 * @Date：2021/3/1 14:23
 **/
public class CurrentHashMapDemo {
    public static void main(String[] args) {
         new HashMap();
        Map a = new ConcurrentHashMap<>();
        a.put("","");
        System.out.println(1 << 1);
        System.out.println(1 << 2);
        System.out.println(1 << 3);
        System.out.println(1 << 30);
        System.out.println(MAXIMUM_CAPACITY >>> 1);
        System.out.println(2 + (2 >>> 1) + 1);
    }
}
