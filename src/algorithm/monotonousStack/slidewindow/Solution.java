package algorithm.monotonousStack.slidewindow;

import java.util.Arrays;

/**
 * //输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * //输出：[3,3,5,5,6,7]
 *
 * @Author： xinjingjie
 * @Date：2021/5/25 9:31
 **/
public class Solution {
    public static void main(String[] args) {
        int[] ints = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        int[] ints2 = {7, 2, 4};
        int k2 = 2;

        int[] ints3 = {1, -1, 2, 3, 1, 1, 0, 1, 1, 1, 1};
        int k3 = 3;

        int[] ints4 ={};
        int k4 = 26779;
        System.out.println(Arrays.toString(maxSlidingWindow(ints, k)));
        System.out.println(Arrays.toString(maxSlidingWindow(ints2, k2)));
        System.out.println(Arrays.toString(maxSlidingWindow(ints3, k3)));
        System.out.println(Arrays.toString(maxSlidingWindow(ints4, k4)));
    }

    //记录最大的，滑动后比较
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        int max = getMax(nums, k, 0);
        System.out.println(max);
        res[0] = max;
        int l = k;
        for (int j = k; j < nums.length; j++) {
//            if (j == k) {
            max = getMax(nums, k, j - (k - 1));
//            }
            if (max < nums[j]) {
                max = nums[j];
                l = k + 1;
            }
            l--;
            if (l == 0) {
                max = getMax(nums, k, j - (k - 1));
            }
            res[j - k + 1] = max;
        }
        return res;
    }

    public static int getMax(int[] nums, int k, int l) {
        int max = nums[l];
        for (int i = 0; i < k; i++) {
            if (max < nums[l + i]) {
                max = nums[l + i];
            }
        }
        return max;
    }
}
