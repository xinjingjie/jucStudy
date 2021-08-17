package understand.annotation.repeatable;

import understand.annotation.MyOrder;

import java.lang.annotation.Annotation;

/**
 * @Author： xinjingjie
 * @Date：2021/6/4 13:40
 **/
@MySelf(name = "s")
@MySelf(name = "b")
@MyOrder(1)
public class TestClass {
public void say(){}
}
