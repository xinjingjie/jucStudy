package stream;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author： xinjingjie
 * @Date：2021/6/9 10:34
 **/
public class List2Map {
    public static void main(String[] args) {
        List<Map<String, String>> list = new ArrayList();
        Map<String, String> a = new HashMap<>();
        a.put("1001", "小A");
        list.add(a);


        Map<String, String> b = new HashMap<>();
        b.put("1002", "小B");
        list.add(b);
        Map<String, String> c = new HashMap<>();
        c.put("1003", "小C");
        list.add(c);
        System.out.println(list);

//        final Map<String, String> collect = list.stream().collect(Collectors.toMap(stringStringMap -> {
//            return new HashMap<>();
//        }));
//        System.out.println(collect);


        a.put("1002", "小B");
        a.put("1003", "小C");
        a.put("1004", "小D");
        for (int i = 0; i < 5000; i++) {
            a.put("" + i, "小" + i);
        }
//        System.out.println(a.values());

        Instant i1=Instant.now();
        String res = String.join(",", a.values());
        Instant i2=Instant.now();

        Instant i3=Instant.now();
        String res2 =a.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.joining(","));
        Instant i4=Instant.now();

        System.out.println(Duration.between(i1,i2).toMillis());
        System.out.println(Duration.between(i3,i4).toMillis());

//        System.out.println(res);
    }


}
