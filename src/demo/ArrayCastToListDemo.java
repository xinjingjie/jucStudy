package demo;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author： xinjingjie
 * @Date：2021/5/13 9:14
 **/
public class ArrayCastToListDemo {
    public static void main(String[] args) {
        //不可以重新赋值，但是可以改变值，正如Array类中的静态内部类ArrayList中的a数组一样
        final String[] s1 = {"1", "2", "3"};
        //s2==s1 ,s2指向s1的地址
        String[] s2 = s1;

        System.out.println("下面验证的是Arrays.asList()方法：");
        List<String> aList = Arrays.asList(s1);
        System.out.println("首先正常输出:");
        for (Object i : aList) {
            System.out.println(i);
        }
        System.out.println("修改index=1的值为9");
        //修改List之后会修改原数组，因为其用的是内部静态类
        System.out.println("此处的值原来为：" + aList.set(1, "9"));
        System.out.println("修改之后s1的值");
        for (String i : s1) {
            System.out.println(i);
        }
        System.out.println("修改之后再Arrays.asList的值");
        List<String> aList2 = Arrays.asList(s1);
        for (String i : aList2) {
            System.out.println(i);
        }
        System.out.println("修改s2的值为10");
        s2[2] = "10";
        System.out.println("修改完s2的值----" + s2);
        for (String i : s2) {
            System.out.println(i);
        }
        System.out.println("修改完s2之后s1的值---- " + s1);
        for (String i : s1) {
            System.out.println(i);
        }
        System.out.println("修改完s2之后s1转集合aList的值---- ");
        for (Object i : aList) {
            System.out.println(i);
        }
        System.out.println("可见修改完一个其他都会变，因为他们引用的都是同一个地址的值");


        System.out.println("此静态内部类没有add方法，所以会报错，可以用个新的集合来存：");
        List nList = new ArrayList(Arrays.asList(s1));
        nList.add("2222");
        for (Object i : nList) {
            System.out.println(i);
        }


        System.out.println("下面验证的是Collections.singletonList方法：");

        List cList3 = Collections.singletonList(s1);
        for (Object i : cList3) {
            System.out.println(i);
        }
        System.out.println("其也是个静态内部类，但是没有set，且其返回的是一个元素，不是数组，用来减少内存");

        System.out.println("可以用Stream.of(s1).collect(Collectors.toList())来变为一个可添加的集合");
        List<String> scList = Stream.of(s1).collect(Collectors.toList());
        scList.add("1");
        scList.add("1");
        scList.add("1");
        for (String i : scList) {
            System.out.println(i);
        }


        System.out.println("int型的和integer型的数组转换");
        int[] ints = {1, 3, 4, 6, 7};
        Integer[] nums = {1, 3, 4, 6, 7};
        char[] chars = {'2','a', 2, 3,'@'};
        boolean[] booleans = {false, true, false};
        System.out.println("int型的数组转换为集合");
        List scList2 = Arrays.stream(ints).boxed().collect(Collectors.toList());
        for (Object i : scList2) {
            System.out.println(i);
        }
        System.out.println("integer型的数组转换为集合 Arrays.asList");
        List<Integer> intsList = Arrays.asList(nums);
        for (Object i : intsList) {
            System.out.println(i);
        }

        System.out.println("integer型的数组转换为集合方法2  ");

        List collect = Arrays.stream(nums).collect(Collectors.toList());
        for (Object i : collect) {
            System.out.println(i);
        }

        System.out.println("stream.Stream.of(xxxx).collect(Collectors.toList())方法测试");
        List charList = Stream.of(chars).collect(Collectors.toList());
        List intList = Stream.of(ints).collect(Collectors.toList());
        List integerList = Stream.of(nums).collect(Collectors.toList());
        List booleansList = Stream.of(booleans).collect(Collectors.toList());
        for (Object i : charList) {
            System.out.println(i);
        }
        for (Object i : intList) {
            System.out.println(i);
        }
        for (Object i : booleansList) {
            System.out.println(i);
        }
        for (Object i : integerList) {
            System.out.println(i);
        }
        System.out.println("可见只有非基本类型可以");

        System.out.println("char[] 不能通过上面方法");

        List charsList = new String(chars)
                .chars()
                .mapToObj(i-> (char) i)
                .collect(Collectors.toList());


        for (Object i : charsList) {
            System.out.println(i);
        }

        System.out.println("boolean[] 不能通过上面方法");
        
    }
}
