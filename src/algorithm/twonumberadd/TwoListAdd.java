package algorithm.twonumberadd;

import java.util.Stack;

/**
 * @Author： xinjingjie
 * @Date：2021/5/10 16:36
 **/
public class TwoListAdd {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int l1Size = getLength(l1);
        int l2Size = getLength(l2);
        //补
        if (l1Size > l2Size) {
            addBack(l2, l1Size - l2Size);
        } else if (l1Size < l2Size) {
            addBack(l1, l2Size - l1Size);
        }
        //相加
        ListNode l = new ListNode();
        ListNode ln = l;
        boolean add = false;
        while (l1 != null) {
            ListNode temp = new ListNode();
            temp.val = l1.val + l2.val;
            if (add) {
                temp.val = temp.val + 1;
                add = false;
            }
            if (temp.val > 9) {
                temp.val = temp.val - 10;
                add = true;
            }
            l1 = l1.next;
            l2 = l2.next;
            ln.next = temp;
            ln = ln.next;
            if (l1 == null && add) {
                ln.next = new ListNode(1, null);
            }
        }
//        System.out.println(l.next);
//        System.out.println(reverse(l.next));
//        return reverse(l.next);
        return l.next;
    }

    public static ListNode reverse(ListNode rawList) {
        Stack<ListNode> stack = new Stack<>();
        ListNode listNode = new ListNode();
        ListNode l = listNode;

        while (rawList != null) {
            stack.push(rawList);
            rawList = rawList.next;
        }

        while (!stack.empty()) {
            l.next = new ListNode(stack.pop().val);
            l = l.next;
        }
//        System.out.println(listNode.next);
        return listNode.next;
    }

    public static int getLength(ListNode temp) {
        int nSize = 1;
        while (temp.next != null) {
            nSize++;
            temp = temp.next;
        }
        return nSize;
    }

    public static void addBack(ListNode l, int cutLength) {
        ListNode temp = l;
        while (temp.next != null) {
            temp = temp.next;
        }
        for (int i = cutLength; i > 0; i--) {
            temp.next = new ListNode(0, null);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, null)))))));
        ListNode listNode2 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, null))));
        addTwoNumbers(listNode1, listNode2);

//        c(listNode1);
//        System.out.println(getLength(listNode1));
//        int num = 1;
//        c2(num);
//        System.out.println(num);
    }

//    public static void c(ListNode listNode) {
//        listNode.next = new ListNode(22222);
//    }
//
//    public static void c2(int x) {
//        x = 2;
//    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
