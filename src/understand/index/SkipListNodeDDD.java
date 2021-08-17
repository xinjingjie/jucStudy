package understand.index;

/**
 * @Author： xinjingjie
 * @Date：2021/8/16 10:19
 **/
public class SkipListNodeDDD {
    int level;
    int obj;
    int score;
    SkipListNodeDDD next;

    public SkipListNodeDDD(int level, int obj) {
        this.level = level;
        this.obj = obj;
    }

    public SkipListNodeDDD() {
    }

}
