package demo;

/**
 * @Author： xinjingjie
 * @Date：2021/5/7 14:16
 * 要求不改动main实现foobar的打印
 **/
public class FoobarDemo {
    private static volatile int number = 0;
    final Object monitor = new Object();
    private final int n;

    public FoobarDemo(int n) {
        this.n = n;
    }

    public static void main(String[] args) {
        FoobarDemo foobarDemo = new FoobarDemo(10);
        new Thread(() -> {
            try {
                foobarDemo.foo(() -> System.out.print("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                foobarDemo.bar(() -> System.out.print("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (monitor) {
                while (number % 2 != 0) {
                    monitor.wait();
                }
                printFoo.run();
                number++;
                monitor.notify();
            }
        }

    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (monitor) {
                while (number % 2 == 0) {
                    monitor.wait();
                }
                printBar.run();
                number++;
                monitor.notify();
            }
        }
    }
}
