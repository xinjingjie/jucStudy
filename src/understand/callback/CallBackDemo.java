package understand.callback;

import java.util.concurrent.CountDownLatch;

/**
 * @Author： xinjingjie
 * @Date：2021/3/22 9:09
 **/
public class CallBackDemo extends RealDo{
    private CountDownLatch countDownLatch=new CountDownLatch(1);
    public void dod() throws InterruptedException {
        ServerDemo serverDemo= new ServerDemo(this);
        serverDemo.start();
        countDownLatch.await();
        serverDemo.run();

    }
    @Override
    public void process() {
        System.out.println("回调成功");
        countDownLatch.countDown();
    }
}
