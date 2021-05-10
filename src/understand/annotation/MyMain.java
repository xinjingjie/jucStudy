package understand.annotation;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author： xinjingjie
 * @Date：2021/3/25 13:37
 **/
public class MyMain {
    private static final List<String> allClass = new CopyOnWriteArrayList<>();
    private static ConcurrentHashMap<Class<?>, Object> beans = new ConcurrentHashMap<>();

    public static void main(String[] args) throws ClassNotFoundException {
        init();
        //实例化类，相当于从ApplicationContext获取实例
        MyTest myTest1 = (MyTest) getBean("MyTest");
        myTest1.name = "1";
        System.out.println(myTest1.name);
        MyTest myTest2 = (MyTest) getBean("MyTest");
        System.out.println(myTest2.name);
        System.out.println(myTest1 == myTest2);
    }

    private static void init() {
        //获取当前包下所有的java文件
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String packagePath = "understand.annotation".replace(".", "/");
        URL url = loader.getResource(packagePath);
        assert url != null;
        File file = new File(url.getFile());
        String[] files = file.list();
        assert files != null;
        allClass.addAll(Arrays.asList(files));
        allClass.removeIf(s -> !s.endsWith(".class"));
        //处理
        execute(allClass);
    }

    private static void execute(List<String> allClass) {
        //根据@MyOrder排序
        Class<?>[] sortedClass = sort(allClass);
        for (Class<?> c:sortedClass){
            System.out.println(c);
        }


        //处理
        for (Class<?> cClass : sortedClass) {
            try {
                process(cClass);
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        System.out.println(beans.values());
    }

    public static Class<?>[] sort(List<String> allClass) {
        Class<?>[] sorted = new Class[allClass.size()];
        List<Class<?>> sortList = new ArrayList<>();
        Map<Class<?>, Integer> waitToSortMap = new ConcurrentHashMap<>();

        for (String cName : allClass) {
            boolean isOrder = false;
            Class<?> aClassA = null;
            int orderValue = 0;
            try {
                aClassA = Class.forName("understand.annotation." + cName.substring(0, cName.length() - 6));
                Annotation[] annotations = aClassA.getAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation.annotationType().equals(MyOrder.class)) {
                        MyOrder myOrder = (MyOrder) annotation;
                        orderValue = myOrder.value();
                        isOrder = true;
                    }
                }
                //不是排序的直接加到sortList
                if (!isOrder) {
                    sortList.add(aClassA);
                } else {
                    //需要排序的加入到待排序的Map中
                    waitToSortMap.put(aClassA, orderValue);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        //排序
        List<Map.Entry<Class<?>, Integer>> infoIds = new ArrayList<>(waitToSortMap.entrySet());
        infoIds.sort(Comparator.comparing(o -> (o.getValue()).toString()));
        //添加
        for (Map.Entry<Class<?>, Integer> infoId : infoIds) {
            sortList.add(infoId.getKey());
        }
        sortList.toArray(sorted);
        return sorted;
    }

    public static void process(Class<?> aClass) throws IllegalAccessException, InstantiationException {
        if (aClass.isInterface() || aClass.isAnnotation()) {
            return;
        }
        boolean isContain = false;
        Annotation[] annotations = aClass.getAnnotations();
        for (Annotation annotation : annotations) {
            //如果包含MyConditionOnBean的话，看看是不是包含
            if (annotation.annotationType().equals(MyConditionOnBean.class)) {
                MyConditionOnBean myConditionOnBean = (MyConditionOnBean) annotation;
                isContain = false;
                for (Class<?> c : myConditionOnBean.value()) {
                    if (beans.containsKey(c)) {
                        beans.put(aClass, aClass.newInstance());
                        isContain = true;
                    }
                }
            }
        }
        if (!isContain) {
            beans.put(aClass, aClass.newInstance());
        }
    }


    public static Object getBean(String name) throws ClassNotFoundException {
        return beans.get(Class.forName("understand.annotation." + name));
    }
}