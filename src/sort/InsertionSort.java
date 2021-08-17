package sort;

import java.util.Arrays;

/**
 * @Author： xinjingjie
 * @Date：2021/8/9 15:03
 * @Describe 先假设第一个已经排序,然后看后面的依次和前面已经排序的对比，插入到正确的位置
 **/
public class InsertionSort implements Sort {
    public static void main(String[] args) {
        int[] nums1 = new int[]{49, 38, 65, 97, 76, 13, 27, 49};
        new InsertionSort().sort(nums1);
        System.out.println(Arrays.toString(nums1));
    }

    @Override
    public void sort(int[] arr) {
        int count = 0;
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                count++;
                if (temp < arr[j]) {
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                } else if (j == (i - 1)) {
                    break;
                }
            }

        }
        System.out.println("count:" + count);
    }
}
