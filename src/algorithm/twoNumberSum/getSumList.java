package algorithm.twoNumberSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author： xinjingjie
 * @Date：2021/5/12 10:41
 **/
public class getSumList {
    public static void main(String[] args) {
        int[] list = {2,7,11,15};
        System.out.println(Arrays.toString(twoSum(list, 9)));
    }

    public static int[] twoSum(int[] nums, int target) {

        // value  position
        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
        int i = 0;
        for (int r : nums) {
            if (hashMap.containsKey(r)) {
                hashMap.get(r).add(i++);
            } else {
                List<Integer> l = new ArrayList<>();
                l.add(i++);
                hashMap.put(r, l);
            }
        }
        System.out.println(hashMap.size());
        List<Integer> f;
        List<Integer> e;
        for (int r : nums) {
            if (hashMap.containsKey(target - r)) {
                f = hashMap.get(r);
                e = hashMap.get(target - r);
                if (f == e && e.size() == 1){
                    continue;
                }
                return new int[]{f.get(0), f == e ? e.get(1) : e.get(0)};
            }
        }
        return new int[2];

    }
}
