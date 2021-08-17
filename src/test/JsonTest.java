package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author： xinjingjie
 * @Date：2021/7/20 15:47
 **/
public class JsonTest {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        JSON json= new JSONArray(list);
        System.out.println(json);

        String s1 = JSON.toJSONString(list);
        System.out.println(s1);

        Map map= new HashMap();
        map.put("a","1");
        map.put("b","2");
        map.put("c","3");
        map.put("d","4");
        String s = JSON.toJSONString(map);
        System.out.println(s);


        HashMap hashMap = JSON.parseObject(s, HashMap.class);
        System.out.println(hashMap.entrySet());

    }
}
