package jvm;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * @Author： xinjingjie
 * @Date：2021/5/8 14:42
 **/
public class MyClassLoaderTest {
    public static void main(String[] args) throws Exception {
        MyClassLoader classLoader = new MyClassLoader("E:/zxy/com/xinjingjie");
        Class clazz = classLoader.loadClass("jvm.User1");
        Object obj = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("say", null);
        method.invoke(obj, null);
        System.out.println(clazz.getClassLoader());

        MyClassLoader classLoader2 = new MyClassLoader("E:/zxy/com/xinjingjie");
        Class clazz2 = classLoader2.loadClass("jvm.User1");
        Object obj2 = clazz2.newInstance();
        Method method2 = clazz2.getDeclaredMethod("say", null);
        method2.invoke(obj2, null);
        System.out.println(clazz2.getClassLoader());
    }

    static class MyClassLoader extends ClassLoader {
        private final String classPath;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        private byte[] loadByte(String name) throws Exception {
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classPath + "/" + name
                    + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadByte(name);
                //defineClass将一个字节数组转为Class对象，这个字节数组是class文件读取后最终的字节数组。
                return defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            if (! "jvm.User1".equals(name)){
                return super.loadClass(name);
            }
            synchronized (getClassLoadingLock(name)) {
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    long t0 = System.nanoTime();
                    long t1 = System.nanoTime();
                    c = findClass(name);
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
                return c;
            }
        }
    }
}
