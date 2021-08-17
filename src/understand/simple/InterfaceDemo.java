package understand.simple;

/**
 * @Author： xinjingjie
 * @Date：2021/7/10 11:30
 **/
public class InterfaceDemo implements I2{
    @Override
    public void i2() {

    }

    @Override
    public void i1() {

    }

    public static void main(String[] args) {
        InterfaceDemo i =new InterfaceDemo();
        System.out.println(i instanceof I1);
        System.out.println(i instanceof I2);
    }
}
