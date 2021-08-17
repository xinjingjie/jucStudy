package algorithm.frogOverRiver;

import java.util.Arrays;

/**
 * @Author： xinjingjie
 * @Date：2021/6/1 15:29
 **/
public class Solution {
    private static Boolean[][] rec;

    public static void main(String[] args) {
//        true
        int[] stones = {0, 1, 3, 5, 6, 8, 12, 17};
        System.out.println(canCross3(stones));

        //false
        int[] stones2 = {0, 1, 2, 3, 4, 8, 9, 11};
        System.out.println(canCross(stones2));

        //false
//        int[] stones3 = {0, 2};
//        System.out.println(canCross(stones3));
//
//        //true
//        int[] stones4 ={0,1,3,6,10,13,14};
//        System.out.println(canCross(stones4));

//        int[] stones5 ={0,1,2,3,4,8,9,11};
//        System.out.println(canCross(stones5));

        int[] stones3 ={9};
        System.out.println(canCross3(stones3));
    }

    public static boolean canCross(int[] stones) {

        if (stones[0] != 0 || stones[1] > 1) {
            return false;
        }
        int s = stones[1] - stones[0];
        int temp = 1;
        int i = 1;
        while (temp < stones.length) {
            i = temp;
            while (temp < stones.length && stones[temp] - stones[i] <= s + 1) {
                temp++;
            }
            temp = --temp;
            if (temp < stones.length && temp == i + 1 && stones[temp] - stones[i] > s + 1) {
                return false;
            }
            if (temp == i) {
                return false;
            }
            if (temp < stones.length) {
                s = stones[temp] - stones[i];
            }
        }
        return true;
    }

    public static boolean canCross2(int[] stones) {
        /*
        假设每次都跳 k + 1 步
        0   1   2   3   4   5   6   ：  i
        1   2   3   4   5   6   7   :   step
        那么第 i 个单位最远能够条 i + 1 步

        对于第 i 个石头，前面的石头 j 能否跳到该石头, 假设两石头间的距离为 diff = stones[i] - stones[j],
        需要判断 j 是否存在可以跳跃 diff 步 的能力，而这个能力是由 石头 j 前面的石头决定的
        当前面能够跳 diff 步石头 i 时，那么石头 i 可跳的步数增加 diff - 1 || diff || diff + 1

        因此，我们记录 石头 i 所有能够跳跃的步数
        dp[i][j] 表示 第 i 个石头是否可以跳 j 步

        比如 当 石头 j 能够跳到石头 i 时， diff = stones[i] - stones[j]
        那么对于 石头 i 来说，它能够跳跃的步数增加了以下三个
                    dp[i][diff - 1] = true;
                    dp[i][diff] = true;
                    dp[i][diff + 1] = true;
        */
        int len = stones.length;

        //dp[i][j] 表示 第 i 个石头是否可以跳 j 步
        boolean[][] dp = new boolean[len][len + 1];

        //初始条件：第 0 个石头可以跳 1 步
        dp[0][1] = true;

        for (int i = 1; i < len; i++) {
            boolean flag = false;
            //因为 石头 i 最大只能跳 i + 1 步，因此 前面的石头 j 到达 石头 i 的距离必须 <= i
            for (int j = i - 1; j >= 0; j--) {
                int diff = stones[i] - stones[j];
                if (diff > i) {
                    break;
                }
                //对于 石头 j ，它需要跳 diff 步
                if (dp[j][diff]) {
                    dp[i][diff - 1] = true;
                    dp[i][diff] = true;
                    dp[i][diff + 1] = true;
                    flag = true;
                }
            }
            //当到达了终点 而 flag ，表示无法从前面的任意石头跳到终点，返回 false
            if (i == len - 1 && !flag) {
                return false;
            }
        }
        return true;
    }

    public static boolean canCross3(int[] stones) {
        int n = stones.length;
        rec = new Boolean[n][n];
        return dfs(stones, 0, 0);
    }

    private static boolean dfs(int[] stones, int i, int lastDis) {
        if (i == stones.length - 1) {
            return true;
        }
        if (rec[i][lastDis] != null) {
            return rec[i][lastDis];
        }

        for (int curDis = lastDis - 1; curDis <= lastDis + 1; curDis++) {
            if (curDis > 0) {
                int j = Arrays.binarySearch(stones, i + 1, stones.length, curDis + stones[i]);
                if (j >= 0 && dfs(stones, j, curDis)) {
                    return rec[i][lastDis] = true;
                }
            }
        }
        return rec[i][lastDis] = false;
    }
}
