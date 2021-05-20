package algorithm.getRain;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author： xinjingjie
 * @Date：2021/5/20 9:44
 **/
public class Solution {
    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(height));
        int[] height2 = {4, 2, 0, 3, 2, 5};
        System.out.println(trap(height2));
        int[] height3 = {0, 1, 2, 3, 4, 5};
        System.out.println(trap(height3));
        int[] height4 = {0, 1, 2, 3, 4, 5, 0, 1, 2};
        System.out.println(trap(height4));


    }

    public static int trap(int[] height) {
        if (height.length == 0){
            return 0;
        }
        /*
      1 -> 2 ;  1  1*1-0 =1
      2 -> 3 ;  3  2*3-1-1 = 4
      2 -> 2 ;  1  2*1-1 =1

      1->2 ;
 */
//        Deque<Integer> stack = new LinkedList<>();
//        int res = 0;
//        int min = 0;
//        int num = 0;
//        int tempRes = 0;
//        boolean isZero = true;
//        List<Integer> middle = new LinkedList<>();
        int re = 0;
        int max = height[0];
        int pos = 0;
        for (int i = 0; i < height.length; i++) {
            if (max < height[i]) {
                max = height[i];
                pos = i;
            }
        }
        return get(height, 0, pos) + get(height, height.length - 1, pos);


//        for (int k : height) {
//            if (isZero) {
//                if (0 == k) {
//                    continue;
//                } else {
//                    isZero = false;
//                }
//            }
//            if (stack.isEmpty() || k < stack.peekLast()) {
//                stack.push(k);
//            } else {
//                int f = stack.peekLast();
//                //大于栈里的值的话
//                while (!stack.isEmpty() && k >= f) {
//                    middle.add(stack.pop());
//                    num++;
//                }
//                if (middle.get(middle.size() - 1) == 0) {
//                    tempRes = k;
//                } else {
//                    tempRes = middle.get(middle.size() - 1) * (num - 1);
//                    for (int j = 0, middleSize = middle.size(); j < middleSize; j++) {
//                        if (j == middleSize - 1) {
//                            continue;
//                        }
//                        int m = middle.get(j);
//                        tempRes -= m;
//                    }
//                }
//                res += tempRes;
//                num = 0;
//                middle.clear();
//                stack.push(k);
//            }
//        }
//        return res;
    }

    public static int get(int[] height, int start, int end) {
        int res = 0;
        Deque<Integer> stack = new LinkedList<>();
        int min = 0;
        int num = 0;
        int tempRes = 0;
        boolean isZero = true;
        List<Integer> middle = new LinkedList<>();

        if (start < end) {
            for (; start <= end; start++) {
                int k = height[start];
                if (isZero) {
                    if (0 == k) {
                        continue;
                    } else {
                        isZero = false;
                    }
                }
                if (stack.isEmpty() || k < stack.peekLast()) {
                    stack.push(k);
                } else {
                    int f = stack.peekLast();
                    //大于栈里的值的话
                    while (!stack.isEmpty() && k >= f) {
                        middle.add(stack.pop());
                        num++;
                    }
                    if (middle.get(middle.size() - 1) == 0) {
                        tempRes = k;
                    } else {
                        tempRes = middle.get(middle.size() - 1) * (num - 1);
                        for (int j = 0, middleSize = middle.size(); j < middleSize; j++) {
                            if (j == middleSize - 1) {
                                continue;
                            }
                            int m = middle.get(j);
                            tempRes -= m;
                        }
                    }
                    res += tempRes;
                    num = 0;
                    middle.clear();
                    stack.push(k);
                }
            }
        }else{
            for (; start >= end; start--) {
                int k = height[start];
                if (isZero) {
                    if (0 == k) {
                        continue;
                    } else {
                        isZero = false;
                    }
                }
                if (stack.isEmpty() || k < stack.peekLast()) {
                    stack.push(k);
                } else {
                    int f = stack.peekLast();
                    //大于栈里的值的话
                    while (!stack.isEmpty() && k >= f) {
                        middle.add(stack.pop());
                        num++;
                    }
                    if (middle.get(middle.size() - 1) == 0) {
                        tempRes = k;
                    } else {
                        tempRes = middle.get(middle.size() - 1) * (num - 1);
                        for (int j = 0, middleSize = middle.size(); j < middleSize; j++) {
                            if (j == middleSize - 1) {
                                continue;
                            }
                            int m = middle.get(j);
                            tempRes -= m;
                        }
                    }
                    res += tempRes;
                    num = 0;
                    middle.clear();
                    stack.push(k);
                }
            }

        }

        return res;
    }
}
