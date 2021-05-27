package algorithm.monotonousStack.slidewindow;

/**
 * @Author： xinjingjie
 * @Date：2021/5/25 14:47
 **/
public class Solution2 {
    public static void main(String[] args) {

    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
//        Vector<Integer> ans;
//        Deque<Integer> d = new LinkedList<>();
//        for(int i=0;i<nums.length;i++)
//        {
//            if(!d.isEmpty()&&d.peekFirst()<i-k+1) {
//                d.pollFirst();
//            }
//            while(!d.isEmpty()&&nums[d.peekLast()]<=nums[i])
//                d.pollLast();
//            d.(i);
//            if(i>=k-1)
//                ans.push_back(nums[d.front()]);
//        }
//        return ans;
        return nums;
    }
}

//双向队列
// int[] ints3 = {1, -1, 2, 3, 1, 1, 0, 1, 1, 1, 1};
//  1， -1， 2  -1,1,2
//-1, 2, 3      -1,2,3
// 2 ,3, 1      1 , 2, 3,
