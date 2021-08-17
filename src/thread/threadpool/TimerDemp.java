package thread.threadpool;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author： xinjingjie
 * @Date：2021/7/9 13:52
 **/
public class TimerDemp {
    public static void main(String[] args) {
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("start");
            }
        }, 1000,1000);
    }
}
