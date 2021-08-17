package understand.index;

/**
 * @Author： xinjingjie
 * @Date：2021/8/16 10:15
 **/
public class SkipList {
    private SkipListNode header;
    private SkipListNode tail;
    private int level;
    private int length;

    public static void main(String[] args) {
        SkipListNode skipListNode = new SkipListNode();

    }

    public static class SkipListNode {
        int level;
        int obj;
        int score;
        SkipListNodeDDD next;

        public SkipListNode(int level, int obj) {
            this.level = level;
            this.obj = obj;
        }

        public SkipListNode() {
        }

    }
}
