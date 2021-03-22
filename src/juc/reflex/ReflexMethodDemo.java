package juc.reflex;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;

/**
 * @Author： xinjingjie
 * @Date：2021/3/18 9:05
 **/
public class ReflexMethodDemo {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        //无参构造方法
        ClassA ca = ClassA.class.newInstance();
        System.out.println("s:" + ca.s + " i:" + ca.i);

        //获取public的构造方法列表
        Constructor<?>[] ca2 = ClassA.class.getDeclaredConstructors();

        System.out.println(ca2.length);
        ca2[0].setAccessible(true);
        //getTypeParameters()方法用于返回TypeVariable数组，该数组表示此GenericDeclaration的通用表示形式声明的变量类型
        System.out.println(Arrays.toString(ca2[0].getTypeParameters()));

        System.out.println(Arrays.toString(ca2[0].getParameterTypes()));

        ClassA ca3 = (ClassA) ca2[0].newInstance(3);
        System.out.println("s:" + ca3.s + " i:" + ca3.i);

        ClassA ca4 = (ClassA) ca2[1].newInstance(2, "y");
        System.out.println("s:" + ca4.s + " i:" + ca4.i);

        ClassA ca5 = (ClassA) ca2[2].newInstance();
        System.out.println("s:" + ca5.s + " i:" + ca5.i);

    }
}



class ClassA {
    int i = 1;
    String s = "x";

    public ClassA() {
    }

    public ClassA(int i, String s) {
        this.i = i;
        this.s = s;
    }

    private ClassA(int i) {
        this.i = i;
        this.s = "z";
    }

    private void setI(int i) {
        this.i = i;
    }

    private void setS(String s) {
        this.s = s;
    }

    public String rs() {
        System.out.println("return s");
        return s;
    }

}