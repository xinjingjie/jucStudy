package algorithm.yanghuitriangle;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author： xinjingjie
 * @Date：2021/6/1 14:01
 **/
public class Solution {
    public static void main(String[] args) {
        System.out.println(generate(10));
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            if (i == 0) {
                list.add(1);
            } else {
                List<Integer> lastOne = res.get(res.size() - 1);
                for (int j = 0; j <= lastOne.size(); j++) {
                    if (j == 0) {
                        list.add(lastOne.get(j));
                    } else if (j == lastOne.size()) {
                        list.add(lastOne.get(j - 1));
                    } else {
                        list.add(lastOne.get(j - 1) + lastOne.get(j));
                    }
                }
            }
            res.add(list);
        }
        return res;
    }
}
