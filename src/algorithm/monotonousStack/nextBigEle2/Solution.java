package algorithm.monotonousStack.nextBigEle2;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @Author： xinjingjie
 * @Date：2021/5/21 15:07
 **/
public class Solution {
    public static void main(String[] args) {
        int[] ints = {1, 1, 1, 1, 1, 2, 3, 4, 5, 2, 1, 3, 4, 2, 1, 0};
        int[] ints2 = {5, 4};
        System.out.println(Arrays.toString(nextGreaterElements(ints)));
        System.out.println(Arrays.toString(nextGreaterElements(ints2)));
    }

    public static int[] nextGreaterElements(int[] nums) {
        int max = nums[0];
        for (int i : nums) {
            if (i > max) {
                max = i;
            }
        }
        Deque<Integer> deque = new ArrayDeque<>();
        int[] res = new int[nums.length];
        boolean isFlag = false;
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[deque.peek()] < nums[i]) {
                res[deque.pop()] = nums[i];
            }
            if (!isFlag && nums[i] != max) {
                deque.push(i);
            }
            if (nums[i] == max) {
                res[i] = -1;
            }
            if (i == nums.length - 1 && !deque.isEmpty()) {
                i = -1;
                isFlag = true;
            }
        }
        return res;
    }
}
