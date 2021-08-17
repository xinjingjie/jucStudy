package sort;

import java.util.Arrays;

/**
 * @Author： xinjingjie
 * @Date：2021/8/9 15:41
 *
 * @Describe 分两组，左边的排序，右边的排序 然后最后排完序合并到一个新数组
 **/
public class MergeSort implements Sort {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 5, 2312, 34, 5, 7, 2, 6, 7, 213, 8};
        new MergeSort().sort(nums1);
        System.out.println(Arrays.toString(nums1));
    }

    public static int[] sort(int[] a, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            sort(a, low, mid);
            sort(a, mid + 1, high);
            //左右归并
            merge(a, low, mid, high);
        }
        return a;
    }

    public static void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = a[j++];
        }
        // 把新数组中的数覆盖nums数组
        for (int x = 0; x < temp.length; x++) {
            a[x + low] = temp[x];
        }
    }


//    public static int[] merge(int[] a, int[] b) {
//        int[] res = new int[a.length + b.length];
//        int all = 0;
//        int i = 0;
//        int j = 0;
//        while (i < a.length && j < b.length) {
//            if (a[i] < b[j]) {
//                res[all++] = a[i++];
//            } else {
//                res[all++] = b[j++];
//            }
//        }
//        while (i < a.length) {
//            res[all++] = a[i++];
//        }
//        while (j < b.length) {
//            res[all++] = a[j++];
//        }
//        return res;
//    }

    @Override
    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }
}
