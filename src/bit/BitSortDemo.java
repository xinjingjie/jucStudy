package bit;

import java.util.Arrays;

/**
 * @Author： xinjingjie
 * @Date：2021/6/30 10:59
 **/
public class BitSortDemo {
    public static void main(String[] args) {
        int[] arr = {1, 7, 3, 0, 0, 6, 6, 9, 11};
        System.out.println(Arrays.toString(sort(arr)));


        int[] arr2 = {-1, 7, 3, 0, 0, 6, 6, 9, -11};
        System.out.println(Arrays.toString(betterSort(arr2)));

    }

    /*
    只能正整数
     */
    public static int[] sort(int[] arr) {
        int[] res = new int[arr.length];
        int l=0;
        int max = arr[0];
        for (int j : arr) {
            if (max < j) {
                max = j;
            }
        }
        int[] temp = new int[max+1];

        for (int x : arr) {
            temp[x] = temp[x] + 1;
        }
        for (int r = 0; r < temp.length; r++) {
            for(int z=0;z<temp[r];z++){
                res[l] = r;
                l++;
            }
        }
        return res;
    }


    public static int[] betterSort(int[] arr) {
        int[] res = new int[arr.length];
        int l=0;
        int max = arr[0];
        int min = arr[0];
        for (int j : arr) {
            if (max < j) {
                max = j;
            }
            if (min >j){
                min = j;
            }
        }
        int ggg = Math.abs(min);
        int[] temp = new int[max-min+1];
        for (int x : arr) {
            temp[x+ggg] = temp[x+ggg] + 1;
        }
        for (int r = 0; r < temp.length; r++) {
            for(int z=0;z<temp[r];z++){
                res[l] = r-ggg;
                l++;
            }
        }
        return res;
    }
}
