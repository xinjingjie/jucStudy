package test;

import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

/**
 * @Author： xinjingjie
 * @Date：2021/4/12 16:28
 **/
public class EnvAndProperties {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        Map<String, String> getenv = System.getenv();

        System.out.println(properties.size());
        System.out.println(getenv.size());
        System.out.println("--------------------系统参数-----------------------------");

        Enumeration<?> x=properties.propertyNames();
        while(x.hasMoreElements()){
            String q= (String) x.nextElement();
            System.out.println(q+" : "+properties.getProperty(q));
        }

        System.out.println("------------------环境变量-------------------------------");
        for (String string :getenv.keySet()){
            System.out.println(string+" : "+getenv.get(string));
        }
    }
}
