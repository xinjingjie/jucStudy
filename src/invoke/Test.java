package invoke;

import java.lang.reflect.Field;

/**
 * @Author： xinjingjie
 * @Date：2021/6/8 19:29
 **/
public class Test {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        User user= new User("zzz","18");

        System.out.println(getValue(user,"name"));
    }

    public static<T> Object getValue(T c,String field) throws NoSuchFieldException, IllegalAccessException {
        Class cClass =c.getClass();
        Field dF = cClass.getDeclaredField(field);
//        dF.setAccessible(true);
        return dF.get(c);
    }
}
