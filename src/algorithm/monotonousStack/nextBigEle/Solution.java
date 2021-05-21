package algorithm.monotonousStack.nextBigEle;

import java.util.*;

/**
 * @Author： xinjingjie
 * @Date：2021/5/21 13:54
 **/
public class Solution {
    public static void main(String[] args) {
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        System.out.println(Arrays.toString(nextGreaterElement(nums1, nums2)));
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> rMap = new HashMap<>();
        int[] res = new int[nums1.length];

        for (int j = 0; j < nums2.length; j++) {
            if (stack.isEmpty() || nums2[stack.peek()] > nums2[j]) {
                stack.push(j);
            } else {
                while (!stack.isEmpty() && nums2[stack.peek()] <= nums2[j]) {
                    rMap.put(nums2[stack.pop()], nums2[j]);
                }
                stack.push(j);
            }
        }

        for (int k = 0; k < nums1.length; k++) {
            res[k] = rMap.getOrDefault(nums1[k], -1);
        }
        return res;
    }

    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len2; i++) {
            while (!stack.isEmpty() && stack.peekLast() < nums2[i]) {
                map.put(stack.removeLast(), nums2[i]);
            }
            stack.addLast(nums2[i]);
        }
        int[] res = new int[len1];
        for (int i = 0; i < len1; i++) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        return res;
    }

}
