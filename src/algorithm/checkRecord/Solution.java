package algorithm.checkRecord;

/**
 * @Author： xinjingjie
 * @Date：2021/8/17 8:51
 **/
public class Solution {
    public static void main(String[] args) {
        //Absent Present Late
        String sta = "PPALLL";
        System.out.println(checkRecord(sta));
    }


    public static boolean checkRecord(String s) {
        byte[] bytes = s.getBytes();
        int aNum = 0;
        int lNum = 0;
        for (byte b : bytes) {
            if (b == 'A') {
                aNum++;
            }
            if (b == 'L') {
                lNum++;
            } else {
                lNum = 0;
            }
            if (lNum > 2 || aNum > 1) {
                return false;
            }
        }
        return true;
    }
}
