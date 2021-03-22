package juc.reflex;

/**
 * @Author： xinjingjie
 * @Date：2021/3/15 15:46
 **/
public class Work {
    int x = 0;
    private int y = 99;
    String z = "nihao";
    String a = "what";

    Work(int p1, int p2) {
        this.x = p1;
        this.y = p2;
    }

    Work() {
    }

    public void did() {
        System.out.println("x: " + x + " y:" + y + " z: " + z + " a: " + a);
    }
}
