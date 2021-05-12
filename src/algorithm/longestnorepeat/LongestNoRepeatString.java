package algorithm.longestnorepeat;

/**
 * @Author： xinjingjie
 * @Date：2021/5/12 11:27
 **/
public class LongestNoRepeatString {
    public static void main(String[] args) {
        String a = "abcacdbzas";
        String b = "abcabcbb";
        String c = "bbbbb";
        String e = "pwwkew";
        String f = "aabaab!bb";
        System.out.println(lengthOfLongestSubstring(a));
        System.out.println(lengthOfLongestSubstring(b));
        System.out.println(lengthOfLongestSubstring(c));
        System.out.println(lengthOfLongestSubstring(e));
        System.out.println(lengthOfLongestSubstring(f));
//6 3 1 3 3
    }

    //acdbz
    //cdbzas

    public static int lengthOfLongestSubstring(String s) {
//        //起始指针
//        int i = 0;
//        //移动指针
//        int j = 0;
        StringBuilder subString = new StringBuilder();
        //最长串
        int maxLength = 0;
        int length;
        //移动过的都不是重复的

        for (int n = 0; n < s.length(); n++) {
            int i1 = subString.toString().indexOf(s.charAt(n));
            //如果不存在，则加入字符串
            if (i1 < 0) {
                subString.append(s.charAt(n));
            } else {
                //否则 从 i1 开始
                length = subString.length();
                if (maxLength < length) {
                    maxLength = length;
                }
                if (maxLength > s.length() - n + length) {
                    return maxLength;
                }
                subString.delete(0, i1 + 1);
                subString.append(s.charAt(n));
            }
        }
        length = subString.length();
        if (maxLength < length) {
            maxLength = length;
        }
        return maxLength;
    }
}
