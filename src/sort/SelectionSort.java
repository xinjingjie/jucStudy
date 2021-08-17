package sort;

import java.util.Arrays;

/**
 * @Author： xinjingjie
 * @Date：2021/8/9 11:07
 * @Describe 在后面的数组里选择最小的放到最前面
 **/
public class SelectionSort implements Sort {
    public static void main(String[] args) {
        int[] nums1 = new int[]{49, 38, 65, 97, 76, 13, 27, 49};
        new SelectionSort().sort(nums1);
        System.out.println(Arrays.toString(nums1));
    }

    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int index = i;
            int min = arr[index];
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < min) {
                    index = j;
                    min = arr[j];
                }
            }
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
    }
}
