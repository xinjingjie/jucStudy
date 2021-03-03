package jvm;

/**
 * @Author： xinjingjie
 * @Date：2021/3/2 9:15
 **/
public class HeapDemo {
    //    public static void main(String[] args) {
//        String zhangsan, lisi;
//        zhangsan = new String("我是对象张三");
//        lisi = new String("我是对象李四");
//        zhangsan = lisi;
//        System.out.println(zhangsan==lisi);
//    }
    public static void main(String args[]) {
        int a = 1;
        int b = 1;
        a = 2;
        System.out.println(a);   //2
        System.out.println(b);  //1
        TT t = new TT("T");
        TT t1 = t;
        t.setName("TT");
        System.out.println(t.getName()); //TT
        System.out.println(t1.getName());//TT
    }
}

class TT {
    private String name;
    public TT(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name1) {
        this.name = name1;
    }
}


