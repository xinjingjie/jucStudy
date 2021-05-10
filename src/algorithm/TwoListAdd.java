package algorithm;

/**
 * @Author： xinjingjie
 * @Date：2021/5/10 16:36
 **/
public class TwoListAdd {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode();
        int l1Size = getLength(l1);
        int l2Size = getLength(l2);
        //补
        if (l1Size > l2Size) {
            addBack(l2, l1Size - l2Size);
        } else if (l1Size < l2Size) {
            addBack(l1, l2Size - l1Size);
        }
        System.out.println(l1);
        System.out.println(l2);

        //相加
        ListNode l = new ListNode();
        ListNode ln = new ListNode();
        boolean x = false;
        while (l1.next != null) {
            ListNode temp = new ListNode();

            if (!x) {
                l.val = l1.val + l2.val;
                x = true;
            } else {
                temp.val = l1.val + l2.val;
            }
            l1 = l1.next;
            l2 = l2.next;
            ln.next = temp;
            l.next = ln;
        }
        System.out.println(l);
        return res;
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
        ListNode listNode1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        ListNode listNode2 = new ListNode(1, null);
        addTwoNumbers(listNode1, listNode2);
    }

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
