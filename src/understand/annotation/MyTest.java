package understand.annotation;

/**
 * @Author： xinjingjie
 * @Date：2021/3/25 13:37
 **/
@MyConditionOnBean(MyCondition.class)
@MyOrder(2)
public class MyTest {
    String name = "zxy";
}
