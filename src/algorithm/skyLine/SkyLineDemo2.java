package algorithm.skyLine;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author： xinjingjie
 * @Date：2021/7/14 15:08
 **/
public class SkyLineDemo2 {
    public static void main(String[] args) {
        int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};

        int[][] buildings2 = {{0, 2, 10}, {2, 4, 15}, {4, 8, 12}, {15, 20, 10}, {19, 24, 8}};
        int[][] buildings3 = {{0, 2, 3}, {2, 5, 3}};
        int[][] buildings4 = {{0, 1, 3}};
        int[][] buildings5 = {{0, 2147483647, 2147483647}};
        int[][] buildings6 = {{0, 10, 10}};
        System.out.println(getSkyline(buildings6));
    }

    public static List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        for (int[] building : buildings) {
            int begin = building[0];
            int end = building[1];
            int height = building[2];


        }

        return res;
    }
}
