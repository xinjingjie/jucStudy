package thread.test;

/**
 * 验证断点是暂停所有线程还是一个线程，idea可以右键选择
 * @Author： xinjingjie
 * @Date：2021/9/3 14:40
 **/
public class TestTwoThread {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                sout(new StringBuilder("A"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            try {
                sout2(new StringBuilder("B"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
    }

    public static void sout(StringBuilder name) throws InterruptedException {
        while (true) {

            System.out.println(name);
        }
    }

    public static void sout2(StringBuilder name) throws InterruptedException {
        while (true) {
            System.out.println(name);
        }
    }
}
