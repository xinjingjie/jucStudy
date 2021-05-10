package algorithm;

/**
 * @Author： xinjingjie
 * @Date：2021/5/7 14:16
 * 要求不改动main实现foobar的打印
 **/
public class FoobarDemo2 {
    private volatile  boolean loopForFoo = false;
    private volatile  boolean loopForBar = true;
    private int n;


    public FoobarDemo2(int n) {
        this.n = n;
    }

    public static void main(String[] args) {
        FoobarDemo2 foobarDemo = new FoobarDemo2(10);
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
            while(loopForFoo){
            }
            printFoo.run();
            loopForFoo=true;
            loopForBar=false;
        }

    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while(loopForBar){
            }
            printBar.run();
            loopForFoo=false;
            loopForBar=true;
        }
    }
}
