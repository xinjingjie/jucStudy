package understand.annotation.repeatable;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * @Author： xinjingjie
 * @Date：2021/6/4 13:48
 **/
public class TestMain {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> c = TestClass.class;
        Annotation[] cAnnotations = c.getDeclaredAnnotations();
        System.out.println(cAnnotations.length);
        for (Annotation a : cAnnotations) {
            System.out.println(a.annotationType());
            if (a.annotationType().equals(MySelfS.class)) {
                MySelfS m = (MySelfS) a;
                System.out.println(Arrays.toString(m.value()));
            }
        }


//        Class a= Class.forName(String.valueOf(TestClass.class));
//        Annotation[] fAnnotations = a.getAnnotations();
//        for (Annotation f :fAnnotations){
//            System.out.println(f.annotationType());
//        }
//        MySelf annotation = (MySelf) c.getAnnotation(MySelf.class);
//        System.out.println(annotation.name());
    }
}
