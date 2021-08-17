package understand.annotation.repeatable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author： xinjingjie
 * @Date：2021/6/4 13:39
 **/
@Retention(RUNTIME)
@Target({ElementType.TYPE})
public @interface MySelfS {
    MySelf[] value();
}
