package understand.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author： xinjingjie
 * @Date：2021/3/26 10:00
 **/
@Retention(RUNTIME)
@Target({ElementType.TYPE})
public @interface MyOrder {
    int value();
}
