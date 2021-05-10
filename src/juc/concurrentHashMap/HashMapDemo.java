package juc.concurrentHashMap;


import java.util.HashMap;
import java.util.Objects;

/**
 * @Author： xinjingjie
 * @Date：2021/3/1 15:22
 **/
public class HashMapDemo {
    static int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {
        String a = "2";
        int b = 1;
        HashMap<Integer, String> hm = new HashMap<>(11);
        hm.put(1, "1");
        hm.put(2, "2");
        hm.put(2, "3");
        hm.put(399, "399");
//        hm.put(2,"2");
//        hm.putIfAbsent(1,"999");
//        hm.put(2,"999");
//        System.out.println(hm.get(1));
//        System.out.println(hm.get(2));
//
//        System.out.println(11<<1); //22
//        // 异或，相同为0，不同为1
//        System.out.println(1 ^ 2);

//        hm.put(new String("x"), 1);
//        System.out.println(hm.get(new String("x")));
//
//        hm.put(new KeyTest("x"), 1);
//        System.out.println(hm.get(new KeyTest("x")));
//
//        System.out.println(new KeyTest("x").hashCode());
//        System.out.println(new KeyTest("x").hashCode());
//        System.out.println(new KeyTest("x").equals(new KeyTest("x")));

        System.out.println("tableSizeFor:---");

//        System.out.println(tableSizeFor(1));
//        System.out.println(tableSizeFor(2));
//        System.out.println(tableSizeFor(3));
//        System.out.println(tableSizeFor(4));
//        System.out.println(tableSizeFor(8));
        System.out.println(tableSizeFor(514));
        System.out.println(513>>>1);
        System.out.println(513>>>2);
        System.out.println(513>>>4);
        System.out.println(513>>>8);
        System.out.println(513>>>16);

//
//        //如果想要存8个值，设置为几好
//        int i = 8 + 8 / 3;
//        System.out.println(i);
//        System.out.println(tableSizeFor(i));


        for (int j = 0; j < 10; j++) {
            int x=j;
            System.out.println("修改" + j + "之前size是:" + tableSizeFor(x));
            if (x < 3) {
                x=x+1;
            } else {
                x = x + x / 3;
            }
            System.out.println("修改" + j + "之后size是:" + tableSizeFor(x));
            System.out.println();
        }
        System.out.println(2|3);
    }

    static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        System.out.println("n"+n);
        n |= n >>> 2;
        System.out.println("n"+n);
        n |= n >>> 4;
        System.out.println("n"+n);
        n |= n >>> 8;
        System.out.println("n"+n);
        n |= n >>> 16;
        System.out.println("n--"+n);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


    static void exceptSize() {
        //        int expectedSize = 5;
//        HashMap hashMap2 = new HashMap(expectedSize < 1073741824 ? expectedSize + expectedSize / 3 : 2147483647);
//        while (true) {
//            Scanner sc = new Scanner(System.in);
//            int ssc = Integer.valueOf(sc.next());
//            System.out.println(ssc + ssc / 3);
//
//
//        }
//        25+8 33
        System.out.println(25 + 25 / 3);
        System.out.println(33 * 0.75);
        System.out.println(13 * 0.75);// 9
        System.out.println(7 * 0.75); // 5
//        Map<String, String> map = Maps.newHashMapWithExpectedSize(10);
    }

    static void hashCodeD() {
        int i = 1;
        Object x = i;
        System.out.println(x.hashCode());
        char[] chars = {'s', 't', 'r'};
        String s = new String(chars);
        System.out.println(s.hashCode());

        int h = 0;
        for (int l = 0; l < chars.length; l++) {
            h = 31 * h + chars[l];
        }
        System.out.println(h);
        System.out.println("gdejicbegh".hashCode());
        System.out.println("hgebcijedg".hashCode());
    }

    static void calcule() {
        String str = new String("");
        System.out.println(hash("2"));
        System.out.println("2".hashCode());
        System.out.println(50 >>> 16);
        System.out.println(50 ^ (50 >>> 16));
        //>> >>>
        System.out.println(7 >> 2);
        System.out.println(7 >>> 2);
        System.out.println((-7) >> 2);
        System.out.println((-7) >>> 2);
    }

    static class KeyTest {
        private String x;

        KeyTest(String x) {
            this.x = x;
        }

        //重写hashCode
        @Override
        public int hashCode() {
            return Objects.hash(x);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            KeyTest keyTest = (KeyTest) o;
            return Objects.equals(x, keyTest.x);
        }
    }
}