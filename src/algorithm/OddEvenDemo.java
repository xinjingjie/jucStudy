package algorithm;

/**
 * @Author： xinjingjie
 * @Date：2021/5/7 15:18
 **/
public class OddEvenDemo {
    private static volatile int number = 0;

    public static void main(String[] args) {
        final Object monitor = new Object();

        /**
         * 奇数线程
         */
        Runnable callable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    boolean interrupted = Thread.currentThread().isInterrupted();
                    if (interrupted) {
                        break;
                    }
                    synchronized (monitor) {
                        while (number % 2 == 0) {
                            try {
                                monitor.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("奇数线程, number:"+number);
                        number++;
                        monitor.notify();
                    }

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread1 = new Thread(callable);
        thread1.setName("odd");
        thread1.start();

        /**
         * 偶数线程
         */
        Runnable evenCallable = new Runnable() {

            @Override
            public void run() {
                while (true) {
                    boolean interrupted = Thread.currentThread().isInterrupted();
                    if (interrupted) {
                        break;
                    }
                    synchronized (monitor) {
                        while (number % 2 != 0) {
                            try {
                                monitor.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("偶数线程, number:"+number);
                        number++;

                        monitor.notify();
                    }


                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(evenCallable);
        thread.setName("even");
        thread.start();


    }
}
