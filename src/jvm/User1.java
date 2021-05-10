package jvm;

/**
 * @Author： xinjingjie
 * @Date：2021/5/8 15:10
 **/
public class User1 {
    static {
        System.out.println("i am user1");
    }

    public static void say(String name) {
        System.out.println(name+" is saying");
    }

    public static void main(String[] args) {
        say("name");
        System.out.println(User1.class.getClassLoader());
    }
}
