package algorithm.carFleet;

import java.util.Arrays;

/**
 * @Author： xinjingjie
 * @Date：2021/8/17 15:24
 **/
public class Solution {
    public static void main(String[] args) {
//        System.out.println(carFleet2(12, new int[]{10, 8, 0, 5, 3}, new int[]{2, 4, 1, 1, 3}));
//        System.out.println(carFleet2(10, new int[]{3}, new int[]{3}));
//        System.out.println(carFleet2(10, new int[]{2, 4}, new int[]{3, 2}));
//        System.out.println(carFleet2(10, new int[]{4, 6}, new int[]{3, 2}));
//


        String a = "03,03,03";
        String b =a.split(",")[0]+",+";
        System.out.println(b);
        if (a.matches(b)){
            System.out.println("match");
        }
    }

//    public static int carFleet(int target, int[] position, int[] speed) {
//        if (position.length <= 1) {
//            return 1;
//        }
//        int n = 0;
//        float[][] res = new float[position.length][position.length];
//        for (int i = 0; i < position.length; i++) {
//            for (int j = 0; j < position.length; j++) {
//                if (i == j) {
//                    res[i][j] = Float.MAX_VALUE;
//                } else {
//                    if ((speed[i] - speed[j]) != 0) {
//                        res[i][j] = (float) (position[j] - position[i]) / (float) (speed[i] - speed[j]);
//                    } else {
//                        res[i][j] = Float.MAX_VALUE;
//                    }
//                }
//            }
//        }
//        System.out.println(Arrays.deepToString(res));
//        for (int i = 0; i < position.length; i++) {
//            for (int j = 0; j < res[i].length; j++) {
//                if (i==j){
//                    continue;
//                }
//                float t = ((float) (target - position[i]) / (float) speed[i]);
//                System.out.println(t);
//                if (res[i][j] <= t && res[j][i] <= t && res[i][j]==res[j][i]) {
//                    n--;
//                    System.out.println(true);
//                }
//            }
//        }
//        return position.length+n/position.length;
//    }
//            System.out.println(Arrays.deepToString(res));



    public static int carFleet2(int target, int[] position, int[] speed) {
        if(position == null || position.length ==0) return 0;
        //由于每辆车的起点不通，故可通过起点左边作为index来记录每一辆车
        int[] temp = new int[target];
        for(int i=0;i<position.length;i++){
            temp[position[i]] = speed[i];
        }

        //用于保存每一辆车到终点的时间
        double[] time = new double[position.length];
        //排序位置，以便于从离终点最近的车开始可能形成的车队
        Arrays.sort(position);
        for(int i=0;i<time.length;i++){
            int n = position.length -1-i;
            //time的顺序为离终点最近的车依次排序
            time[i] = (target-position[n])/(1.0*temp[position[n]]);
        }

        int ans = 1;
        for(int i=0;i<time.length-1;i++){
            if(time[i]>=time[i+1]){
                //如果后面的车比前面的车按速度算先到终点，那么组成一个车队，时间与前面的车保持一致
                time[i+1] = time[i];
            }else{
                ans++;
            }
        }
        return ans;
    }

}
