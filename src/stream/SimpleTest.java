package stream;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author： xinjingjie
 * @Date：2021/7/15 15:17
 **/
public class SimpleTest {
    public static void main(String[] args) {

        List<String> codes =new ArrayList<>();
        codes.add("640000001");
        codes.add("640000002");
        Object o = JSON.toJSON(codes);
        System.out.println(o.getClass());

        List<String> list=JSONArray.parseObject(o.toString(),List.class);
        for (String e:list){
        }

        System.out.println();
    }

}
