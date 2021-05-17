package algorithm.findmedianSorted;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author： xinjingjie
 * @Date：2021/5/12 16:30
 **/
public class Solution {
    public static void main(String[] args) {
        int[] nums1 = {1, 3, 4, 6, 7};

        String[] s2={"1","2","3"};
        int[] nums2 = {2, 4, 6};
//        System.out.println(findMedianSortedArrays(nums1, nums2));

//        ArrayList<String> aList = new ArrayList(Collections.singletonList(nums1));
        ArrayList<String> aList3 = new ArrayList(Collections.singletonList(nums1));

        ArrayList<String> aList2 = new ArrayList<>(Arrays.asList(s2));
        ArrayList<String> aList4 = new ArrayList(Collections.singletonList(s2));
//        List<K> authcChannels = Stream.of(K).collect(Collectors.toList());

//        for (String i:aList){
//            System.out.println(i);
//        }
        System.out.println("---");
        for (String i:aList2){
            System.out.println(i);
        }
        for (String i:aList4){
            System.out.println(i);
        }


    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        List aList = Collections.singletonList(nums2);
//        //两个数组合并
//        for (int n2 : nums2) {
//            aList.add(n2);
//        }

        return 1d;
    }


    public static int binarySearch(Integer[] srcArray, int des) {
        //定义初始最小、最大索引
        int start = 0;
        int end = srcArray.length - 1;
        //确保不会出现重复查找，越界
        while (start <= end) {
            //计算出中间索引值
            int middle = (end + start)>>>1 ;//防止溢出
            if (des == srcArray[middle]) {
                return middle;
                //判断下限
            } else if (des < srcArray[middle]) {
                end = middle - 1;
                //判断上限
            } else {
                start = middle + 1;
            }
        }
        //若没有，则返回-1
        return -1;
    }
}
