package jvm;

/**
 * @Author： xinjingjie
 * @Date：2021/6/1 9:27
 **/
public class GcTest {
    public static void main(String[] args) throws InterruptedException {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6,allocation7;
        allocation1 = new byte[60000 * 1024];

        allocation2 = new byte[8000 * 1024];

        allocation3 = new byte[1000 * 1024];
//        allocation4 = new byte[1000 * 1024];
        allocation5 = new byte[15000 * 1024];
        allocation6 = new byte[20000 * 1024];
        allocation7 = new byte[20000 * 1024];
        allocation4 = new byte[30000 * 1024];
        byte[] allocation8 = new byte[90000 * 1024];
        byte[] allocation9 = new byte[300000 * 1024];
        byte[] allocation219 = new byte[300000 * 1024];
        byte[] allocation229 = new byte[500000 * 1024];
        byte[] allocation230 = new byte[10000 * 1024];
        byte[] allocation231 = new byte[60000 * 1024];
//1383424K
    }
}
