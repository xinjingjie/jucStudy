package test;

/**
 * @Author： xinjingjie
 * @Date：2021/4/13 13:20
 **/
public class Temp extends A {
    public static void main(String[] args) {
        Temp t = new Temp();
        t.say();
    }

    @Override
    void say() {
        System.out.println("Temp ask");
        ask();
    }
}

class A {
    void say() {
        System.out.println("A say");
    }

    void ask() {
        System.out.println("A ask");
    }
}
