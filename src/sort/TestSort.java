package sort;

import java.util.Arrays;

/**
 * @Author： xinjingjie
 * @Date：2021/8/9 11:12
 **/
public class TestSort {
    public static void main(String[] args) {
        int[] nums1 = new int[]{49, 38, 65, 97, 76, 13, 27, 49};
        int[] nums2 = new int[]{5, 234, 7, 87, 2, 435, 2, 0, 1};
        int[] nums3 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

        Sort sort1 = new BubbleSort();
        Sort sort2 = new SelectionSort();
        Sort sort3 = new QuickSort();

        sort1.sort(nums1);
        sort1.sort(nums2);
        sort1.sort(nums3);

        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
        System.out.println(Arrays.toString(nums3));

        sort2.sort(nums1);
        sort2.sort(nums2);
        sort2.sort(nums3);


        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
        System.out.println(Arrays.toString(nums3));

        sort3.sort(nums1);
        sort3.sort(nums2);
        sort3.sort(nums3);

        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
        System.out.println(Arrays.toString(nums3));
    }

}
