package understand.simple;

import java.util.Random;

/**
 * @Author： xinjingjie
 * @Date：2021/8/16 11:29
 **/
public class R {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(new Random().nextDouble());
        }
        System.out.println(Math.E);
    }
}
