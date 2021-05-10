package test;

import org.junit.Test;
import sun.misc.Unsafe;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author： xinjingjie
 * @Date：2021/3/5 19:19
 **/
public class MyTest {
    public static void main(String[] args) throws ParseException {
//        CountDownLatch countDownLatch = new CountDownLatch(1);
//        ReentrantLock lock = new ReentrantLock();
//        int a = 0;
//        for (int i = 0; i < 3; i++) {
//            new Thread(() -> test(lock, a)).start();
//        }
        Date date = Date.from(Instant.now());

        System.out.println(date);
        String s = "Thu Mar 25 19:29:22 CST 2021";
//        DateFormat dateFormat =new SimpleDateFormat();
//        Date date1= dateFormat.parse(s);
//        System.out.println(date1);

        Date date2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(s);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(date2));

        //System.out.println(df.format(date));
//        Date date2 = new Date();
//        date2.setTime(date1);
//        String transformTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
//        System.out.println(transformTime);


        LocalDateTime l = LocalDateTime.now();
        System.out.println(l);


    }


    static void test(ReentrantLock lock, int i) {

//        try {
        while (i <= 36) {

//                for (int j = 0; j < 3; j++) {
            System.out.println(Thread.currentThread().getName() + "--" + i++);
//                }
        }
//        } finally {
//            lock.unlock();
//        }
    }

    @Test(expected = SecurityException.class)
    public void testSingletonGetter() throws Exception {
        Unsafe.getUnsafe();
    }
}
