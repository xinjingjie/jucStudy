package algorithm.bigthannow;

import java.util.Arrays;
import java.util.Stack;

/**
 * @Author： xinjingjie
 * @Date：2021/5/17 16:00
 **/
public class Solution {
    public static void main(String[] args) {
        int[] nums = {1, 9, 5, 6, 6, 9, 4,23,32423,5435,65,654,1,23,4354,6,56445,654654,765,3,4234,566,45123,1231,3453,5,23,32423,5435,65,654,1,23,4354,6,56445,654654,765,3,4234,566,45123,1231,3453,5,23,32423,5435,65,654,1,23,4354,6,56445,654654,765,3,4234,566,45123,1231,3453,5,23,32423,5435,65,654,1,23,4354,6,56445,654654,765,3,4234,566,45123,1231,3453,5,23,32423,5435,65,654,1,23,4354,6,56445,654654,765,3,4234,566,45123,1231,3453,5};
        int[] nums2 = Arrays.copyOf(nums,nums.length);

        int[] nums3 =nums2.clone();

        long l1=System.currentTimeMillis();
        m(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println(System.currentTimeMillis()-l1);
        long l2=System.currentTimeMillis();
        sort(nums2);
        System.out.println(Arrays.toString(nums2));
        System.out.println(System.currentTimeMillis()-l2);


    }


        public static void m(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        for (int num : nums) {
            if (stack.isEmpty() || stack.peek() > num) {
                stack.add(num);
            } else {
                while (!stack.isEmpty() && stack.peek() < num) {
                    stack2.add(stack.pop());
                }
                stack.add(num);
                while (!stack2.isEmpty()) {
                    stack.add(stack2.pop());
                }
            }
        }
        for (int i=0;i< nums.length;i++){
            nums[i] = stack.pop();
        }
    }

    public static void sort(int[] arr){
        for(int i = 0; i < arr.length - 1 ; i++){
            int min = i; // 遍历的区间最小的值
            for (int j = i + 1; j < arr.length ;j++){
                if(arr[j] < arr[min]){
                    // 找到当前遍历区间最小的值的索引
                    min = j;
                }
            }
            if(min != i){
                // 发生了调换
                int temp =  arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
            }
        }
    }
}
