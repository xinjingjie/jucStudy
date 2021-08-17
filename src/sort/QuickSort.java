package sort;

/**
 * @Author： xinjingjie
 * @Date：2021/7/30 16:39
 * @Describe  一个基准值 ，定义两个指针，从右边开始，找到第一个比基准小的值，换到基准的位置
 * 然后从左开始，找到第一个比基准大的值，和右边的基准换位置
 * 重复，直到基准值在中间
 **/
public class QuickSort implements Sort {
    @Override
    public void sort(int[] arr) {
        quickSort2(arr, 0, arr.length - 1);
    }

    public void quickSort2(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int l = left;
        int r = right;
        int key = arr[l];
        while (l < r) {
            while (l < r && arr[r] >= key) {
                r--;
            }
            arr[l] = arr[r];
            arr[r] = key;
            while (l < r && arr[l] <= key) {
                l++;
            }
            arr[r] = arr[l];
            arr[l] = key;
        }

        quickSort2(arr, left, l - 1);
         quickSort2(arr, r + 1, right);
    }


    public void quickSort(int[] arr, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return;
        }
        int left = leftIndex;
        int right = rightIndex;
        //待排序的第一个元素作为基准值
        int key = arr[left];

        //从左右两边交替扫描，直到left = right
        while (left < right) {
            while (right > left && arr[right] >= key) {
                //从右往左扫描，找到第一个比基准值小的元素
                right--;
            }

            //找到这种元素将arr[right]放入arr[left]中
            arr[left] = arr[right];

            while (left < right && arr[left] <= key) {
                //从左往右扫描，找到第一个比基准值大的元素
                left++;
            }

            //找到这种元素将arr[left]放入arr[right]中
            arr[right] = arr[left];
        }
        //基准值归位
        arr[left] = key;
        //对基准值左边的元素进行递归排序
        quickSort(arr, leftIndex, left - 1);
        //对基准值右边的元素进行递归排序。
        quickSort(arr, right + 1, rightIndex);
    }


}
