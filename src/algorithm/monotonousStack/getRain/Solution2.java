package algorithm.monotonousStack.getRain;

/**
 * @Author： xinjingjie
 * @Date：2021/5/20 17:28
 **/
public class Solution2 {
    public static void main(String[] args) {
//        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        System.out.println(trap(height));
//        int[] height2 = {4, 2, 0, 3, 2, 5};
//        System.out.println(trap(height2));
        int[] height3 = {0, 1, 2, 3, 0, 4};
        System.out.println(trap(height3));
//        int[] height4 = {0, 1, 2, 3, 4, 5, 0, 1, 2};
//        System.out.println(trap(height4));
    }

    //找出左边最大和右边最大  最小的那个-自身的高度
    public static int trap(int[] height) {
        int[] left = new int[height.length];
        int maxLeft = Integer.MIN_VALUE;
        int[] right = new int[height.length];
        int maxright = Integer.MIN_VALUE;
        for (int i = 0; i < height.length; i++) {
            maxLeft = Math.max(height[i], maxLeft);
            left[i] = maxLeft;
        }
        for (int i = height.length - 1; i >= 0; i--) {
            maxright = Math.max(height[i], maxright);
            right[i] = maxright;
        }
        int result = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            result += Math.max(0, Math.min(right[i], left[i]) - height[i]);
        }
        return result;
    }
}
