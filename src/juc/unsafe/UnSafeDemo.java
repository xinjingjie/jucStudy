package juc.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author： xinjingjie
 * @Date：2021/3/15 13:39
 **/
public class UnSafeDemo {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {


        /*
        //获取unsafe的方法 1

        Constructor<Unsafe> unsafeConstructor = Unsafe.class.getDeclaredConstructor();
        unsafeConstructor.setAccessible(true);
        Unsafe unsafe = unsafeConstructor.newInstance();

        //获取unsafe的方法 2
        Field field = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe=(sun.misc.Unsafe) field.get(null);
*/
        //获取unsafe的方法 3
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);

        long xl = unsafe.objectFieldOffset(Work.class.getDeclaredField("x"));
        long yl = unsafe.objectFieldOffset(Work.class.getDeclaredField("y"));

        System.out.println(xl);
        System.out.println(yl);
        System.out.println(unsafe.objectFieldOffset(Work.class.getDeclaredField("a")));

        Work w = new Work(1, 99);


//        Work w2 = new Work(1,2);
        unsafe.compareAndSwapObject(w, 24, "what", "how");
        w.did();
        System.out.println(unsafe.objectFieldOffset(Work.class.getDeclaredField("x")));
        ;

        System.out.println(unsafe.objectFieldOffset(Work.class.getDeclaredField("z")));

    }

    static class Work {
        int x = 0;
        int y = 99;
        String z = "nihao";
        String a = "what";

        Work(int p1, int p2) {
            this.x = p1;
            this.y = p2;
        }

        public void did() {
            System.out.println("x: " + x + " y:" + y + " z: " + z + " a: " + a);
        }
    }
}
