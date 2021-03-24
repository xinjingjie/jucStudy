package test;

import javax.management.monitor.Monitor;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author： xinjingjie
 * @Date：2021/3/22 14:25
 **/
public class MapTest {
    public static void main(String[] args) {
        Map<String,Integer> map =new HashMap<>();
        map.put("1",1);
        map.put("2",1);
        map.put("3",1);
        map.put("4",1);
        map.compute("4",(k,v)-> v=10);
        System.out.println(map.get("4"));
    }
}
