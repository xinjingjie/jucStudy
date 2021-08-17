package test;

/**
 * @Author： xinjingjie
 * @Date：2021/6/11 15:57
 **/
public class InnerClassTest {

    public static void main(String[] args) {
        InnerClassTest i=new InnerClassTest();
//        Inner inner= Inner();
    }

    class Inner {
         {
            System.out.println("nmsl");
        }
        public void s(){
            System.out.println("xxx");
        }
    }
}
