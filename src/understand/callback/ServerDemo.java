package understand.callback;

/**
 * @Author： xinjingjie
 * @Date：2021/3/22 9:10
 **/
public class ServerDemo {
    RealDo realDo;

    public ServerDemo(RealDo realDo) {
        this.realDo = realDo;
    }

    public void start() throws InterruptedException {
        System.out.println("Server is starting");
        System.out.println("i am walking");
        System.out.println("回调中");
        Thread.sleep(1000);
        realDo.process();
    }

    public void run(){
        System.out.println("i am running");
    }


}

abstract class RealDo {
    public abstract void process();
}
