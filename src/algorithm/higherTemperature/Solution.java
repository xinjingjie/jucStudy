package algorithm.higherTemperature;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2
 * //, 1, 1, 0, 0]。
 *
 * @Author： xinjingjie
 * @Date：2021/5/17 14:50
 **/
public class Solution {
    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] temperatures2 = {10, 20, 50, 70, 90, 80, 30, 80};
        System.out.println(Arrays.toString(dailyTemperatures(temperatures2)));
        System.out.println(Arrays.toString(dailyTemperatures2(temperatures2)));
        System.out.println(Arrays.toString(dailyTemperatures(temperatures)));
        System.out.println(Arrays.toString(dailyTemperatures2(temperatures)));
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[i] < temperatures[j]) {
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }

    // 可以维护一个存储下标的单调栈，从栈底到栈顶的下标对应的温度列表中的温度依次递减。
    // 如果一个下标在单调栈里，则表示尚未找到下一次温度更高的下标。
    public static int[] dailyTemperatures2(int[] temperatures) {
        int length = temperatures.length;
        int[] ans = new int[length];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            int temperature = temperatures[i];
            while (!stack.isEmpty() && temperature > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }



}
