package juc;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author： xinjingjie
 * @Date：2021/6/28 16:48
 **/
public class ArrayListRemoveTest {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> a= new ArrayList<>();
            for (int i=0;i<500;i++) {
                a.add(i);
            };
        a.removeIf(v->v%2==0);
        System.out.println(a);
    }
}
