package bit;

import java.util.Arrays;

/**
 * @Author： xinjingjie
 * @Date：2021/6/30 10:43
 **/
public class DuplicatedArrayTest {


    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 5, 3, 5, 56, 534, 3, 32},
                {1, 2, 3, 5},
                {1, 2, 3, 5, 3, 5},
                {0, 0, 1, 2, 3, 5, 56, 534, 78, 32}
        };
        for (int[] ints : arr) {
            System.out.println("数组"+ Arrays.toString(ints) +"中" + (hasDuplicatedItem(ints) ? "存在" : "不存在") + "重复元素");
        }
    }

    public static boolean hasDuplicatedItem(int[] arr) {
// 扫描数组找最大值
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // 按最大值创建一个新数组
        int[] bitArray = new int[max + 1];
// 按值向新数组中添值，如value为3则bitArray[3]=1
        for (int value : arr) {
            if (bitArray[value] != 0) {
// 如果value指向的位置已经不是零，说明之前已经给这一块置1了，立即返回true表示数组有重复
                return true;
            } else {
// value指向的位置是零,则置为1表示这一位已经有数存在了
                bitArray[value] = 1;
            }
        }
        return false;
    }
}


