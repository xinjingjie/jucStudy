package understand.deque;

import java.util.LinkedList;

/**
 * @Author： xinjingjie
 * @Date：2021/7/13 16:40
 **/
public class DequeDemo {
    public static void main(String[] args) {
        LinkedList<Integer> sets = new LinkedList<>();
        sets.add(1);
        sets.add(2);
        sets.add(3);
        sets.add(4);
        sets.add(5);
        System.out.println(sets.peekLast());
        System.out.println(sets.peek());

        Integer integer = sets.pollLast();
        System.out.println(integer);
        sets.add(6);
        sets.add(7);
        System.out.println(sets);
    }

}
