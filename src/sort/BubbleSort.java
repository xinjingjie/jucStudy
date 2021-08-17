package sort;

/**
 * @Author： xinjingjie
 * @Date：2021/8/9 10:59
 * @Describe： 每次循环找到最大的放到最后
 **/
public class BubbleSort implements Sort {
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j + 1] < arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
