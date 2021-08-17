package understand.annotation.repeatable;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author： xinjingjie
 * @Date：2021/6/4 13:38
 **/
@Retention(RUNTIME)
@Target({ElementType.TYPE})
@Repeatable(MySelfS.class)
public @interface MySelf {
    String name() default "";
}
