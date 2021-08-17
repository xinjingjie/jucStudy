package algorithm.hanming;

import java.math.BigInteger;

/**
 * @Author： xinjingjie
 * @Date：2021/6/1 14:19
 **/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getDistance2(2, 4));
        //内置函数
        System.out.println(Integer.bitCount(2 ^ 4));
        System.out.println(getDistance3(2, 4));

        System.out.println(4 >> 1);
        System.out.println(16 >> 4);
        System.out.println(16 << 4);
    }

    //汉明距离广泛应用于多个领域。在编码理论中用于错误检测，在信息论中量化字符串之间的差异。
    //
    //两个整数之间的汉明距离是对应位置上数字不同的位数。
    public static int getDistance3(int x, int y) {
        int d = 0;
        int z = x ^ y;
        while (z != 0) {
            z = z & (z - 1);
            d++;
        }
        return d;
    }


    public static int getDistance2(int x, int y) {
        int d = 0;
        int z = x ^ y;
        while (z != 0) {
            d += z & 1;
            z >>= 1;
        }
        return d;
    }

    public static int getDistance(int x, int y) {
        int max = Math.max(x, y);
        int min = x == max ? y : x;
        String sMax = String.format("%08d", new BigInteger(new BigInteger(String.valueOf(max), 10).toString(2)));
        String sMin = String.format("%08d", new BigInteger(new BigInteger(String.valueOf(min), 10).toString(2)));
        int res = 0;
        for (int i = 0; i < sMax.length(); i++) {
            res = sMax.charAt(i) == sMin.charAt(i) ? res : ++res;
        }

        return res;
    }


}
