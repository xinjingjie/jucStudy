package juc.reflex;

import juc.TestReentrantLock;
import juc.unsafe.UnSafeDemo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author： xinjingjie
 * @Date：2021/3/15 15:22
 **/
public class Demo1 {
    public static void main(String[] args) throws IllegalAccessException {
        Class<Work> workClass = Work.class;

        Method[] allMethods = workClass.getDeclaredMethods();
        for (Method m : allMethods) {
            System.out.println(m.getName());
        }
        Field[] fields = workClass.getDeclaredFields();
        Work w = new Work();

        for (Field field : fields) {
            System.out.println(field.getName());
            if ("x".equals(field.getName())) {
                field.setInt(w, 99);
            }
        }
        w.a = "1";
        w.x = 1;
        System.out.println(w.x);
    }
}
