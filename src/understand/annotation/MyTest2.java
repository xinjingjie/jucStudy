package understand.annotation;

import com.sun.istack.internal.Nullable;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author： xinjingjie
 * @Date：2021/3/26 13:31
 **/
@MyOrder(0)
@MyConditionOnBean({MyTest.class,MyCondition.class})
public class MyTest2 {

    private final static Map<String, String> aliasMap = new ConcurrentHashMap<>(16);

    public static void main(String[] args) throws Exception {
        ss("1","@","2" );



        //checkForAliasCircle(name, alias);
        //aliasMap.put(alias, name);
//        r("B","A");
//        r("C","B");
//        r("A","C");

//        r("1","2");
//        r("2","3");
//        r("1","3");
//        r("A","B");
//        r("B","C");
//        aliasMap.forEach((key,value)-> System.out.println(key+" "+value));
//        System.out.println("--");
//        r("A","C");
        r("1","1");
        r("3","1");
//        r("3","1");
        aliasMap.forEach((key,value)-> System.out.println(key+" "+value));

    }

    static void ss(String... strings){
        for (String s:strings){
            System.out.println(s);
        }
    }

    public static void r(String name, String alias) throws Exception {
        if (hasAlias(name,alias)){
            throw new Exception("Cannot register alias '" + alias +
                    "' for name '" + name + "': Circular reference - '" +
                    name + "' is a direct or indirect alias for '" + alias + "' already");
        }
        aliasMap.put(alias, name);
        System.out.println(false);
    }
    //确保添加的没有name和alias值相反的数据且alias和name不相等
    //当两个name相同时，alias相等时返回true ，或者已存的此name的alias 为name时的值与姚村的alais相等时
//    public static boolean hasAlias(String name, String alias) {
//        for (Map.Entry<String, String> entry : aliasMap.entrySet()) {
//            String registeredName = entry.getValue();
//            if (registeredName.equals(name)) {
//                String registeredAlias = entry.getKey();
//                if (registeredAlias.equals(alias) || hasAlias(registeredAlias, alias)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    public static boolean hasAlias(String name, String alias) {
        String registeredName = aliasMap.get(alias);
        return nullSafeEquals(registeredName, name) || (registeredName != null
                && hasAlias(name, registeredName));
    }
    public static boolean nullSafeEquals(@Nullable Object o1,@Nullable Object o2) {
        if (o1 == o2) {
            return true;
        }
        if (o1 == null || o2 == null) {
            return false;
        }
        if (o1.equals(o2)) {
            return true;
        }
        if (o1.getClass().isArray() && o2.getClass().isArray()) {
            return arrayEquals(o1, o2);
        }
        return false;
    }
    private static boolean arrayEquals(Object o1, Object o2) {
        if (o1 instanceof Object[] && o2 instanceof Object[]) {
            return Arrays.equals((Object[]) o1, (Object[]) o2);
        }
        if (o1 instanceof boolean[] && o2 instanceof boolean[]) {
            return Arrays.equals((boolean[]) o1, (boolean[]) o2);
        }
        if (o1 instanceof byte[] && o2 instanceof byte[]) {
            return Arrays.equals((byte[]) o1, (byte[]) o2);
        }
        if (o1 instanceof char[] && o2 instanceof char[]) {
            return Arrays.equals((char[]) o1, (char[]) o2);
        }
        if (o1 instanceof double[] && o2 instanceof double[]) {
            return Arrays.equals((double[]) o1, (double[]) o2);
        }
        if (o1 instanceof float[] && o2 instanceof float[]) {
            return Arrays.equals((float[]) o1, (float[]) o2);
        }
        if (o1 instanceof int[] && o2 instanceof int[]) {
            return Arrays.equals((int[]) o1, (int[]) o2);
        }
        if (o1 instanceof long[] && o2 instanceof long[]) {
            return Arrays.equals((long[]) o1, (long[]) o2);
        }
        if (o1 instanceof short[] && o2 instanceof short[]) {
            return Arrays.equals((short[]) o1, (short[]) o2);
        }
        return false;
    }
}
